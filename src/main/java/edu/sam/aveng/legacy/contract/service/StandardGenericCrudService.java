package edu.sam.aveng.legacy.contract.service;

import edu.sam.aveng.legacy.contract.converter.ICollectionConverter;
import edu.sam.aveng.legacy.contract.dao.IGenericDao;
import edu.sam.aveng.base.contract.model.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@Transactional
public abstract class StandardGenericCrudService<E extends Serializable & Identifiable,
        D>
        implements ICrudService<E, D, D, ICollectionConverter<E, D>> {

    protected ICollectionConverter<E, D> converter;
    protected IGenericDao<E> dao;

    @Autowired
    @Qualifier("genericHiberDao")
    public void setDao(IGenericDao<E> dao) {
        this.dao = dao;
    }

    @Override
    public abstract void converter(ICollectionConverter<E, D> converter);

    public void setConverter(ICollectionConverter<E, D> converter) {
        this.converter = converter;
    }

    // ToDo check if there is a way to assign a primary bean impl

    @Override
    public void entityType(Class<E> type) {
        dao.setClazz(type);
    }

    @Override
    public void create(D dto) {
        E entity = converter.convertToEntity(dto);
        dao.create(entity);
    }

    @Override
    public D findOne(long id) {
        E entity = dao.find(id);
        return converter.convertToDto(entity);
    }

    @Override
    public List<D> findAll() {
        List<E> entities = dao.findAll();
        return converter.convertToDto(entities).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, D dto) {
        E entity = converter.convertToEntity(dto);
        entity.setId(id);
        dao.update(entity);
    }

    @Override
    public void delete(long id) {
        dao.delete(dao.find(id));
    }
}

    /*
        entity.stream()
                .map(item -> converter.convertToDto(item))
                .collect(Collectors.toList());
     */