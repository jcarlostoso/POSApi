package com.jtorresDev.posAPI.abstract_services;

import com.jtorresDev.posAPI.models.request.SaleRequest;
import com.jtorresDev.posAPI.models.response.SaleResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ISaleService extends ICrudService<SaleRequest, SaleResponse,Long>{
        Page<SaleResponse> salesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable, SortType sortType);
}
