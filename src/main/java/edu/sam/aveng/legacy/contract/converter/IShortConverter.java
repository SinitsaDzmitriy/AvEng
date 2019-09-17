package edu.sam.aveng.legacy.contract.converter;

import java.util.Collection;
import java.util.stream.Stream;

public interface IShortConverter<E, D, M> extends ICollectionConverter<E, D> {

    Stream<M> convertToShortDto(Collection<E> entities);
}
