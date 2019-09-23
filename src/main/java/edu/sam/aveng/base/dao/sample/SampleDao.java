package edu.sam.aveng.base.dao.sample;

import edu.sam.aveng.base.contract.dao.AbstractGenericHibernateDao;
import edu.sam.aveng.base.model.entity.Sample;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleDao
        extends AbstractGenericHibernateDao<Sample>
        implements ISampleDao {

    @Override
    protected void assignEntityClazz() {
        setEntityClass(Sample.class);
    }

    @Override
    public Sample findByContent(String content) {
        return getCurrentSession()
                .createQuery("from " + Sample.class.getName() + " s "
                        + "where s.content=:sampleContent", Sample.class)
                .setParameter("sampleContent", content)
                .uniqueResult();
    }

    public List<Sample> likeSearch(List<String> likeCriterias) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("from ")
                .append(Sample.class.getName())
                .append(" s");

        if (likeCriterias != null && likeCriterias.size() > 0) {

            queryBuilder.append(" where s.content like ")
                    .append(likeCriterias.get(0));

            for (int i = 1; i < likeCriterias.size(); i++) {
                queryBuilder.append(" or s.content like")
                        .append(likeCriterias.get(i));
            }
        }

        return getCurrentSession()
                .createQuery(queryBuilder.toString(), Sample.class)
                .list();
    }

    @Override
    public List<Sample> fullTextSearch(String searchQuery) {

        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());

        QueryBuilder sentenceQueryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Sample.class).get();

        org.apache.lucene.search.Query query = sentenceQueryBuilder
                .keyword()
                .onFields("content")
                .matching(searchQuery)
                .createQuery();

        org.hibernate.query.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Sample.class);

        List<Sample> samples = hibQuery.list();

        if(samples.size() == 0) {
            samples = null;
        }

        return samples;
    }
}
