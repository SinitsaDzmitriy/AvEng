package edu.sam.aveng.base.contract.dao;

import edu.sam.aveng.base.contract.model.Identifiable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public abstract class AbstractGenericHibernateDao<T extends Identifiable & Serializable> implements IGenericDao<T> {

    private Class<T> entityClass;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected AbstractGenericHibernateDao() {
        assignEntityClazz();
        if (entityClass == null) {
            throw new IllegalStateException("Entity class wasn't assigned "
                    + "after assignEntityClazz() method execution in super class constructor"
                    + "WARNING: setEntityClass() method must be called only once "
                    + "within assignEntityClazz() that is in turn evoked implicitly "
                    + "through base class constructor.");
        }
    }

    protected abstract void assignEntityClazz();

    protected void setEntityClass(Class<T> entityClass) {
        if(this.entityClass == null) {
            this.entityClass = entityClass;
        } else {
            throw new IllegalStateException("Entity class has been already assign. "
                    + "WARNING: setEntityClass() method must be called only once "
                    + "within assignEntityClazz() that is in turn evoked implicitly "
                    + "through base class constructor.");
        }
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public long persist(final T entity) {
        getCurrentSession().persist(entityClass.getName(), entity);
        return entity.getId();
    }

    @Override
    public T find(final long id) {
        return getCurrentSession().find(entityClass, id);
    }

    public T findEagerly(final long id) {
        return (T) getCurrentSession()
                .createQuery("from " + entityClass.getName() + " t"
                        + " fetch all properties"
                        + " where t.id=:idValue")
                .setParameter("idValue", id)
                .uniqueResult();
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession()
                .createQuery("from " + entityClass.getName())
                .list();
    }

    @Override
    public List<T> findAllEagerly() {
        return (List<T>) getCurrentSession()
                .createQuery("from " + entityClass.getName()
                        + " fetch all properties")
                .list();
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void delete(long entityId) {
        T entity = find(entityId);
        if(entity != null){
            delete(entity);
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
