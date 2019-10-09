package edu.sam.aveng.base.contract.v2.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
    long persist(final T entity);

    T find(final long id);

    T findEagerly(final long id);

    List<T> findAll();

    List<T> findAllEagerly();

    void update(final T entity);

    void delete(final T entity);

    void delete(final long entityId);
}
