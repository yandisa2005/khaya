package com.sb.intro.filters;

import com.sb.intro.entities.IdEntiity;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface Filter<E extends IdEntiity, V extends Serializable, T extends Filter> {

    T sort(Integer page, Integer size, String sort, Sort.Direction order);

    T filter();

    List<E> entities();

    T andConvert();

    Long totalEntities();

    V results();
}
