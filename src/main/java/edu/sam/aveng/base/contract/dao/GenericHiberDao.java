package edu.sam.aveng.base.contract.dao;

import edu.sam.aveng.base.contract.model.Identifiable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


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
    public List<T> findAllEagerlyByProperty(String property, String value) {
        return (List<T>) getCurrentSession()
                .createQuery(String.format("from %s c "
                                + "join fetch "
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

        T response =(T) getCurrentSession()
                .createQuery(String.format("from %s c ", clazz.getName())
                                + String.format("where c.%s=:value", property))
                .setParameter("value", value)
                .uniqueResult();

        return response;
    }

    @Override
    public void deleteByProperty(String property, Object value) {
        getCurrentSession().createQuery(
                "delete from " + clazz.getName() + " e"
                        + " where  e." + property + "=:value")
                .setParameter("value", value)
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
