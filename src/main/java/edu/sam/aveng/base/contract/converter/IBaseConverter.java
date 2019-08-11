package edu.sam.aveng.base.contract.converter;

public interface IBaseConverter<E, D> {

    D convertToDto(E entity);

    E convertToEntity(D dto);
}
