package edu.sam.aveng.legacy.contract.service;

import edu.sam.aveng.legacy.contract.converter.ICollectionConverter;

import java.util.List;

public interface ICrudService<E, D, M, C extends ICollectionConverter<E, D>> {

    void create(D dto);

    List<M> findAll();

    D findOne(long id);

    void update(Long id, D dto);

    void delete(long id);

    void entityType(Class<E> type);

    void converter(C converter);
}
