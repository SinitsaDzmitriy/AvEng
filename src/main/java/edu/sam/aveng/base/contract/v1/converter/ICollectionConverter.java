package edu.sam.aveng.base.contract.v1.converter;

import java.util.Collection;
import java.util.stream.Stream;

public interface ICollectionConverter<E, D> extends IBaseConverter<E, D> {

    Stream<D> convertToDto(Collection<E> entities);

    Stream<E> convertToEntity(Collection<D> dtos);

}
