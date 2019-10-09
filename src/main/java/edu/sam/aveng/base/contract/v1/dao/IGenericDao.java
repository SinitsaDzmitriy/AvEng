package edu.sam.aveng.base.contract.v1.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    long create(final T entity);

    T find(final long id);

    T findByProperty(String property, Object value);

    T findEagerlyByProperty(String property, Object value);

    List<T> findAll();

    List<T> findAllEagerlyByProperty(String property, Object value);

    void updatePropertyById(String property, Object value, Long id);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    void deleteByProperty(String property, Object value);

    Object getPropertyById(String property, Long id);

    List<T> findAllWithLikeCriterias(String targetProperty, List<String> likeCriterias);

    void setClazz(Class<T> clazzToSet);

}
