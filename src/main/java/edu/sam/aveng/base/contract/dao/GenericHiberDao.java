package edu.sam.aveng.base.contract.dao;

import edu.sam.aveng.base.contract.model.Identifiable;
import edu.sam.aveng.base.model.domain.enumeration.Lang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class GenericHiberDao<T extends Serializable & Identifiable>
        implements IGenericDao<T> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Override
    public long create(T entity) {
        Session session = getCurrentSession();
        session.persist(clazz.getName(), entity);
        return entity.getId();
    }

    @Override
    public T findOne(long id) {
        return getCurrentSession().find(clazz, id);
    }

    @Override
    public T findOneEagerlyByProperty(String property, String value) {
        return (T) getCurrentSession()
                .createQuery("from " + clazz.getName() + " t"
                        + " fetch all properties"
                        + " where t." + property + "=:value")
                .setParameter("value", value)
                .uniqueResult();
    }


    @Override
    public List<T> findAllEagerlyByProperty(String property, Object value) {
        return (List<T>) getCurrentSession()
                .createQuery(String.format("from %s c "
                                + "fetch all properties "
                                + "where c.%s='%s'",
                        clazz.getName(), property, value))
                .list();
    }

    public List<T> findAll() {
        return getCurrentSession()
                .createQuery("from " + clazz.getName())
                .list();
    }

    @Override
    public Object getPropertyById(String property, Long id) {
        return getCurrentSession()
                .createQuery("select e." + property
                        + " from " + clazz.getName() + " e"
                        + " where e.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    @Override
    public void updatePropertyById(String property, Object value, Long id) {
        getCurrentSession().createQuery(
                "update " + clazz.getName() + " e"
                        + " set " + property + "=:value"
                        + " where  e.id=:id")
                .setParameter("value", value)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public T findByProperty(String property, String value) {

        return (T) getCurrentSession()
                .createQuery(String.format("from %s c ", clazz.getName())
                        + String.format("where c.%s=:value", property))
                .setParameter("value", value)
                .uniqueResult();
    }

    @Override
    public void deleteByProperty(String property, Object value) {
        getCurrentSession().createQuery(
                "delete from " + clazz.getName() + " e"
                        + " where  e." + property + "=:value")
                .setParameter("value", value)
                .executeUpdate();
    }

    @Override
    public List<T> findWithLikeCriterias(String targetProperty, List<String> likeCriterias) {

        /*
            ToDo: Add check if target field is textual
            This trouble is gone if we follow single responsibility principle
         */


        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(String.format("from %s c", clazz.getName()));

        if (likeCriterias != null && likeCriterias.size() > 0) {

            queryBuilder.append(String.format(" where c.%s like %s", targetProperty, likeCriterias.get(0)));

            for (int i = 1; i < likeCriterias.size(); i++) {
                queryBuilder.append(String.format(" or c.%s like %s", targetProperty, likeCriterias.get(i)));
            }
        }

        return (List<T>) getCurrentSession()
                .createQuery(queryBuilder.toString())
                .list();
    }

    // ToDo: Move this method to CardDao (single responsibility)
    public List<Map> search(Lang usedLang, Lang desiredLang, String formattedSearchInput) {

        Session currentSession =  getCurrentSession();

        List<Map> cardSearchResults = currentSession.createQuery(
                "select new map(" +
                        "c.id as id, " +
                        "c.content as content, " +
                        "c.type as type, " +
                        "p.transcription as transcription, " +
                        "c.definition as definition" +
                        ") from Card c " +
                        "join c.pron p " +
                        "where c.content=:input" +
                        " and c.lang=:inputLang", Map.class)
                .setParameter("input", formattedSearchInput)
                .setParameter("inputLang", usedLang)
                .list();

        for(Map singleResult : cardSearchResults) {

            List<Map> coupledCards = currentSession.createQuery(
                    "select new map(" +
                            "dc.id as id, " +
                            "dc.content as content" +
                            ") from Card c " +
                            "join c.cardMappings cms " +
                            "join cms.destCard dc " +
                            "where c.id=:id and dc.lang=:desiredLang", Map.class)
                    .setParameter("id", singleResult.get("id"))
                    .setParameter("desiredLang", desiredLang)
                    .list();

            singleResult.put("coupledCards", coupledCards);

        }

        return cardSearchResults;
    }


    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
