package edu.sam.aveng.base.contract.v1.dao;

import edu.sam.aveng.base.contract.v2.model.Identifiable;
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
    private SessionFactory sessionFactory;
    private Class<T> clazz;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public long create(T entity) {
        getCurrentSession().persist(clazz.getName(), entity);
        return entity.getId();
    }

    @Override
    public T find(long id) {
        return getCurrentSession().find(clazz, id);
    }

    @Override
    public T findByProperty(String property, Object value) {
        return (T) getCurrentSession()
                .createQuery("from " + clazz.getName() + " c "
                        + "where c." + property + "=:value")
                .setParameter("value", value)
                .uniqueResult();
    }

    @Override
    public T findEagerlyByProperty(String property, Object value) {
        return (T) getCurrentSession()
                .createQuery("from " + clazz.getName() + " t"
                        + " fetch all properties"
                        + " where t." + property + "=:value")
                .setParameter("value", value)
                .uniqueResult();
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession()
                .createQuery("from " + clazz.getName())
                .list();
    }

    @Override
    public List<T> findAllEagerlyByProperty(String property, Object value) {
        return (List<T>) getCurrentSession()
                .createQuery("from " + clazz.getName() + " c "
                                + "fetch all properties "
                                + "where c." + property + "=:value")
                .setParameter("value", value)
                .list();
    }

    @Override
    public List<T> findAllWithLikeCriterias(String targetProperty, List<String> likeCriterias) {
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

    @Override
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

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        T entity = find(entityId);
        delete(entity);
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
    public Object getPropertyById(String property, Long id) {
        return getCurrentSession()
                .createQuery("select e." + property
                        + " from " + clazz.getName() + " e"
                        + " where e.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
