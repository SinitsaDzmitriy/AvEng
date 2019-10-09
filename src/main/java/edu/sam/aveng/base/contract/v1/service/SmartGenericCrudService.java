package edu.sam.aveng.base.contract.v1.service;

import edu.sam.aveng.base.contract.v1.converter.IShortConverter;
import edu.sam.aveng.base.contract.v1.dao.IGenericDao;
import edu.sam.aveng.base.contract.v2.model.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public abstract class SmartGenericCrudService<E extends Serializable & Identifiable,
        D, M>
        implements ICrudService<E, D, M, IShortConverter<E, D, M>> {

    protected IShortConverter<E, D, M> converter;
    protected IGenericDao<E> dao;

    @Override
    public abstract void converter(IShortConverter<E, D, M> converter);

    @Autowired
    @Qualifier("genericHiberDao")
    public void setDao(IGenericDao<E> dao) {
        this.dao = dao;
    }

    public void setConverter(IShortConverter<E, D, M> converter) {
        this.converter = converter;
    }

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
        D dto = converter.convertToDto(entity);
        return dto;
    }

    @Override
    public List<M> findAll() {
        List<E> entities = dao.findAll();
        return converter.convertToShortDto(entities).collect(Collectors.toList());
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
