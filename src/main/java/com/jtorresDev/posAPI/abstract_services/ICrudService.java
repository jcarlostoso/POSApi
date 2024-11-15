package com.jtorresDev.posAPI.abstract_services;

import com.jtorresDev.posAPI.util.enums.SortType;
import org.springframework.data.domain.Page;

public interface ICrudService<RQ, RS, ID> {
    RS create(RQ request);

    RS read(ID id);

    Page<RS> realAll(Integer page, Integer size, SortType sortType);

    RS update(RQ request, ID id);

    void delete(ID id);
    String FIELD_BY_SORT = "id";
}
