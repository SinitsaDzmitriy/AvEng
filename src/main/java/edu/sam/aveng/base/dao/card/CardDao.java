package edu.sam.aveng.base.dao.card;

import edu.sam.aveng.base.contract.dao.AbstractGenericHibernateDao;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.dto.CardTableItem;
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
    public List<CardTableItem> findAllAsTableItems() {
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
                .list();
    }

    @Override
    public List<Map> search(Lang cardLang, Lang coupledCardsLang, String formattedSearchInput) {

        Session session =  getCurrentSession();

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
                        + "where c.content=:input "
                        + "and c.lang=:cardLang", Map.class)
                .setParameter("input", formattedSearchInput)
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
