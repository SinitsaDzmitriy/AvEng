package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.entity.DemoSample;
import edu.sam.aveng.base.contract.v1.dao.IGenericDao;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demo-samples/search")
public class DemoRestController {

    private SessionFactory sessionFactory;

    private IGenericDao<DemoSample> demoSampleDao;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setDemoSampleDao(IGenericDao<DemoSample> sentenceDao) {
        sentenceDao.setClazz(DemoSample.class);
        this.demoSampleDao = sentenceDao;
    }

    @GetMapping("/like")
    @Transactional
    public Map<String, Object> like(@RequestParam String input) throws IOException {

        Map<String, Object> response = new HashMap<>();

        List<String> words = tokenize(input, new StandardAnalyzer());
        List<List<String>> permutations = getPermutations(words);
        List<String> likeCriterias = new ArrayList<>();

        int wordsCount = words.size();
        int permutationsCount = permutations.size();

        if (permutationsCount == 1) {

            String criteriaToMatchStringBeginning = "'" + permutations.get(0).get(0) + " %'";
            String criteriaToMatchStringMiddle = "'% " + permutations.get(0).get(0) + " %'";
            String criteriaToMatchStringDotEnd = "'% " + permutations.get(0).get(0) + ".'";
            String criteriaToMatchStringExclamationEnd = "'% " + permutations.get(0).get(0) + "!'";

            likeCriterias.add(criteriaToMatchStringBeginning);
            likeCriterias.add(criteriaToMatchStringMiddle);
            likeCriterias.add(criteriaToMatchStringDotEnd);
            likeCriterias.add(criteriaToMatchStringExclamationEnd);

        } else {
            for (List<String> permutation : permutations) {

                StringBuilder firstTermStart = new StringBuilder()
                        .append("'")
                        .append(permutation.get(0));

                StringBuilder firstTermMiddle = new StringBuilder()
                        .append("'% ")
                        .append(permutation.get(0));

                StringBuilder lastTermMiddle = new StringBuilder()
                        .append(" %")
                        .append(permutation.get(wordsCount - 1))
                        .append(".'");

                StringBuilder lastTermEnd = new StringBuilder()
                        .append(" %")
                        .append(permutation.get(wordsCount - 1))
                        .append(" %'");

                StringBuilder criteriaCommonPart = new StringBuilder();

                for (int i = 1; i < wordsCount - 1; i++) {
                    criteriaCommonPart
                            .append(" %")
                            .append(permutation.get(i));
                }

                String middleCriteria = new StringBuilder()
                        .append(firstTermMiddle)
                        .append(criteriaCommonPart)
                        .append(lastTermMiddle)
                        .toString();

                String startEndCriteria = new StringBuilder()
                        .append(firstTermStart)
                        .append(criteriaCommonPart)
                        .append(lastTermEnd)
                        .toString();

                String startCriteria = new StringBuilder()
                        .append(firstTermStart)
                        .append(criteriaCommonPart)
                        .append(lastTermMiddle)
                        .toString();

                String endCriteria = new StringBuilder()
                        .append(firstTermMiddle)
                        .append(criteriaCommonPart)
                        .append(lastTermEnd)
                        .toString();

                likeCriterias.add(middleCriteria);
                likeCriterias.add(startEndCriteria);
                likeCriterias.add(startCriteria);
                likeCriterias.add(endCriteria);
            }
        }

        System.out.println(likeCriterias);

        long startTime = System.nanoTime();

        List<DemoSample> demoSamples = demoSampleDao
                .findWithLikeCriterias("content", likeCriterias);

        long endTime = System.nanoTime();

        demoSamples.sort(Comparator.comparing(DemoSample::getId));
        double elapsedTimeInSeconds = (double) (endTime - startTime) / 1_000_000_000.0;

        response.put("demoSamples", demoSamples);
        response.put("time", elapsedTimeInSeconds);

        return response;
    }

    @GetMapping("/full-text")
    @Transactional
    public Map<String, Object> fullTextSearch(@RequestParam String input) {
        Map<String, Object> response = new HashMap<>();

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder sentenceQueryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(DemoSample.class).get();

        long startTime = System.nanoTime();

        org.apache.lucene.search.Query query = sentenceQueryBuilder
                .simpleQueryString()
                .onFields("content")
                .withAndAsDefaultOperator()
                .matching(input)
                .createQuery();

        org.hibernate.query.Query hibQuery =
                fullTextSession.createFullTextQuery(query, DemoSample.class);

        List<DemoSample> demoSamples = new ArrayList<>(hibQuery.list());

        long endTime = System.nanoTime();

        tx.commit();
        fullTextSession.close();

        demoSamples.sort(Comparator.comparing(DemoSample::getId));
        double elapsedTimeInSeconds = (double) (endTime - startTime) / 1_000_000_000.0;

        response.put("demoSamples", demoSamples);
        response.put("time", elapsedTimeInSeconds);

        return response;
    }

    private List<String> tokenize(String text, Analyzer analyzer) throws IOException {
        List<String> result = new ArrayList<String>();
        TokenStream tokenStream = analyzer.tokenStream("text", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result.add(attr.toString());
        }
        return result;
    }

    private List<List<String>> getPermutations(List<String> words) {
        List<List<String>> permutations;
        int numberOfWords = words.size();

        switch (numberOfWords) {
            case 1:
                // One word => only one permutation.
                // 1. Create list for all permutations with initial capacity = 1
                // 2. Create list for the only permutation containing the passed word
                permutations = new ArrayList<>(1);

                List<String> singlePermutation = new ArrayList<>(1);
                singlePermutation.add(words.get(0));

                permutations.add(singlePermutation);

                break;

            case 2:
                /*
                    Two words (recursion endpoint) => 2 permutations: direct and reverse order of elements
                    1. Create list for all permutations with initial capacity = 2
                    2. Create list for the permutation with direct word order
                    3. Create list for the permutation with reverse word order
                */
                permutations = new ArrayList<>(2);

                List<String> directPermutation = new ArrayList<>(2);
                directPermutation.add(words.get(0));
                directPermutation.add(words.get(1));

                List<String> reversePermutation = new ArrayList<>(2);
                reversePermutation.add(words.get(1));
                reversePermutation.add(words.get(0));

                permutations.add(directPermutation);
                permutations.add(reversePermutation);

                break;

            default:
                /*
                    1. Count number of permutations.
                    2. Create list for permutation.
                */
                int numberOfPermutations;

                // calculate permutations count.
                numberOfPermutations = 1;

                for (int i = 2; i <= numberOfWords; i++) {
                    numberOfPermutations *= i;
                }

                // Create list for permutations
                permutations = new ArrayList<>(numberOfPermutations);

                int numberOfTilePermutations;

                for (int i = 0; i < numberOfWords; i++) {
                    String permutationStartWord = words.get(i);

                    List<String> tileWords = new ArrayList<String>(numberOfWords);
                    tileWords.addAll(words);
                    tileWords.remove(permutationStartWord);

                    List<List<String>> tilePermutations = getPermutations(tileWords);
                    numberOfTilePermutations = tilePermutations.size();

                    List<String> permutation;

                    for (int j = 0; j < numberOfTilePermutations; j++) {
                        permutation = new ArrayList<>(numberOfWords);
                        permutation.add(permutationStartWord);
                        permutation.addAll(tilePermutations.get(j));

                        permutations.add(permutation);
                    }
                }
        }
        return permutations;
    }
}