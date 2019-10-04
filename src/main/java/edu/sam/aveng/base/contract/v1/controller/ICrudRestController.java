package edu.sam.aveng.base.contract.v1.controller;

import java.util.List;

public interface ICrudRestController<D, M> {

    void create(D dto);

    D findOne(long id);

    List<M> findAll();

    void update(long id, D dto);

    void delete(long id);

}
