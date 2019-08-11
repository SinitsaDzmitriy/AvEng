package edu.sam.aveng.base.contract.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
    void setClazz(Class<T> clazzToSet);

    long create(final T entity);

    T findOne(final long id);

    T findByProperty(String property, String value);

    T findOneEagerlyByProperty(String property, String value);

    Object getPropertyById(String property, Long id);

    void updatePropertyById(String property, Object value, Long id);

    List<T> findAll();

    List<T> findAllEagerlyByProperty(String property, String value);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    void deleteByProperty(String property, Object value);

}
