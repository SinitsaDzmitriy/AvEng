package edu.sam.aveng.legacy.controller.rest;

import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.legacy.contract.dao.IGenericDao;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestRestController {

    private SessionFactory sessionFactory;

    private IGenericDao<Sample> sampleDao;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setSampleDao(IGenericDao<Sample> sentenceDao) {
        sentenceDao.setClazz(Sample.class);
        this.sampleDao = sentenceDao;
    }

    @GetMapping("/samples/search/like")
    @Transactional
    public Map<String, Object> like(@RequestParam String input) {

        Map<String, Object> response = new HashMap<>();

        long startTime = System.nanoTime();

        List<Sample> samples = sampleDao
                .findWithLikeCriterias("sentence", new SampleSearchInputConverter().convertToLikeCriterias(input));

        long endTime = System.nanoTime();

        double elapsedTimeInSeconds = (double) (endTime - startTime) / 1_000_000_000.0;

        response.put("samples", samples);
        response.put("time", elapsedTimeInSeconds);

        return response;
    }

    @GetMapping("/samples/search/full-text")
    @Transactional
    public Map<String, Object> fullTextSearch(@RequestParam String input) {

        Map<String, Object> response = new HashMap<>();

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder sentenceQueryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Sample.class).get();

        long startTime = System.nanoTime();

        org.apache.lucene.search.Query query = sentenceQueryBuilder
                .keyword()
                .onFields("sentence")
                .matching(input)
                .createQuery();

        org.hibernate.query.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Sample.class);

        List<Sample> samples = new ArrayList<>(hibQuery.list());

        long endTime = System.nanoTime();

        tx.commit();
        fullTextSession.close();

        double elapsedTimeInSeconds = (double) (endTime - startTime) / 1_000_000_000.0;

        response.put("samples", samples);
        response.put("time", elapsedTimeInSeconds);

        return response;
    }

}
