package edu.sam.aveng.base.dao.card;

import edu.sam.aveng.base.contract.v2.dao.AbstractGenericHibernateDao;
import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.CardTableItem;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings("unchecked")
public class CardDao
        extends AbstractGenericHibernateDao<Card>
        implements ICardDao {

    @Override
    protected void assignEntityClazz() {
        setEntityClass(Card.class);
    }

    @Override
    public List<CardTableItem> findAllAsTableItems(int maxNumberOfResults, int firstResultPosition) {
        return getCurrentSession().createQuery("select new "
                + CardTableItem.class.getName()
                + "("
                + "c.id, "
                + "c.lang, "
                + "c.content, "
                + "c.type, "
                + "c.definition"
                + ") "
                + "from " + Card.class.getName() + " c", CardTableItem.class)
                .setFirstResult(firstResultPosition)
                .setMaxResults(maxNumberOfResults)
                .list();
    }

    @Override
    public long countAll() {
        return (long) getCurrentSession().createQuery("select count(*) from "
                + Card.class.getName()).uniqueResult();
    }

    @Override
    public List<Map> search(Lang cardLang, Lang coupledCardsLang, String formattedSearchInput) {
        Session session =  getCurrentSession();
        List<String> likeCriterias = new SampleSearchInputConverter()
                .convertToLikeCriterias(formattedSearchInput);

        List<Map> cardSearchResults = session.createQuery(
                "select new map"
                        + "("
                        + "c.id as id, "
                        + "c.content as content, "
                        + "c.type as type, "
                        + "p.transcription as transcription, "
                        + "c.definition as definition"
                        + ")"
                        + "from Card c "
                        + "join c.pron p "
                        + "where c.lang=:cardLang and "
                        + "("
                        + "c.content like " + likeCriterias.get(0) + " or "
                        + "c.content like " + likeCriterias.get(1)
                        + ")", Map.class)
                .setParameter("cardLang", cardLang)
                .list();

        for(Map singleResult : cardSearchResults) {

            List<Map> coupledCards = session.createQuery(
                    "select new map"
                            + "("
                            + "dc.id as id, "
                            + "dc.content as content"
                            + ") "
                            + "from Card c "
                            + "join c.cardMappings cms "
                            + "join cms.destCard dc "
                            + "where c.id=:id and dc.lang=:targetLang", Map.class)
                    .setParameter("id", singleResult.get("id"))
                    .setParameter("targetLang", coupledCardsLang)
                    .list();

            singleResult.put("coupledCards", coupledCards);
        }

        return cardSearchResults;
    }

}
