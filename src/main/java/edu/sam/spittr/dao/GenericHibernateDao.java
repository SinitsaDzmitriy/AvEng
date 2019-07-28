package edu.sam.spittr.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDao<T extends Serializable>
        implements IGenericDao<T> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public void create(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public T findOne(long id) {
        return getCurrentSession().find(clazz, id);
    }

    public List<T> findAll() {
        return getCurrentSession()
                .createQuery("from " + clazz.getName())
                .list();
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
