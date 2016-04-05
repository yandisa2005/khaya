package com.sb.intro.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by livious on 2015/12/09.
 */
public interface Converter<A extends Serializable, M extends Serializable> {

    Logger log = LoggerFactory.getLogger(Converter.class);

    Optional<A> toModel(M model);

    default List<A> toModels(Collection<M> models) {
        return models.stream().map((model) -> toModel(model).get()).collect(toList());
    }

    default Optional<M> toDomain(A api) {
        return null;
    }

    default List<M> toDomains(Collection<A> apis) {
        return apis.stream().map((model) -> toDomain(model).get()).collect(toList());
    }

}