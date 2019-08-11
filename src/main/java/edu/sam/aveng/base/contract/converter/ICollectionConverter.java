package edu.sam.aveng.base.contract.converter;

import java.util.Collection;
import java.util.stream.Stream;

public interface ICollectionConverter<E, D> extends IBaseConverter<E, D> {

    Stream<D> convertToDto(Collection<E> entities);

    Stream<E> convertToEntity(Collection<D> dtos);

}
