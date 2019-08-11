package edu.sam.aveng.base.contract.controller;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.model.Identifiable;
import edu.sam.aveng.base.contract.service.ICrudService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractCrudRestController<E extends Identifiable,
        D, M, S extends ICrudService<E, D, M, ? extends ICollectionConverter<E, D>>>
        implements ICrudRestController<D, M> {

    protected S service;

    @PostConstruct
    private void setEntityType() {
        service.entityType(entityType());
    }

    protected abstract Class<E> entityType();

    public abstract void service(S service);

    @Override
    @PostMapping("/create")
    public void create(@RequestBody D dto) {
        service.create(dto);
    }

    @Override
    @GetMapping("/{id}")
    public D findOne(@PathVariable long id) {
        return service.findOne(id);
    }

    @Override
    @GetMapping
    public List<M> findAll() {
        return service.findAll();
    }

    @Override
    @PatchMapping("/update/{id}")
    public void update(@PathVariable long id, @RequestBody D dto) {
        service.update(id, dto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}