package edu.sam.aveng.base.contract.v1.converter;

public interface IBaseConverter<E, D> {

    D convertToDto(E entity);

    E convertToEntity(D dto);
}
