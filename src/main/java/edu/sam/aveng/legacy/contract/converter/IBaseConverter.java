package edu.sam.aveng.legacy.contract.converter;

public interface IBaseConverter<E, D> {

    D convertToDto(E entity);

    E convertToEntity(D dto);
}
