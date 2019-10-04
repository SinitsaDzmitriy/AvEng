package edu.sam.aveng.legacy.contract.dao;

import edu.sam.aveng.base.model.enumeration.Lang;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericDao<T extends Serializable> {

    long create(final T entity);

    T find(final long id);

    T findByProperty(String property, Object value);

    T findEagerlyByProperty(String property, String value);

    List<T> findAll();

    List<T> findAllEagerlyByProperty(String property, Object value);

    void updatePropertyById(String property, Object value, Long id);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    void deleteByProperty(String property, Object value);

    Object getPropertyById(String property, Long id);

    List<T> findWithLikeCriterias(String targetProperty, List<String> likeCriterias);

    void setClazz(Class<T> clazzToSet);

}
