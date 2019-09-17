package edu.sam.aveng.base.contract.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    long create(final T entity);

    T find(final long id);

    T findEagerly(final long id);

    List<T> findAll();

    List<T> findAllEagerly();

    T update(final T entity);

    void delete(final T entity);

    void delete(final long entityId);

}
