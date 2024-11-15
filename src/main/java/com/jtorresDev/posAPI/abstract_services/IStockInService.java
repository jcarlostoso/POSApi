package com.jtorresDev.posAPI.abstract_services;


import com.jtorresDev.posAPI.models.request.StockInRequest;
import com.jtorresDev.posAPI.models.response.StockInResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import java.util.Set;

public interface IStockInService extends ICrudService<StockInRequest, StockInResponse,Long> {
   //Page<StockInResponse> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
   // Set<StockInEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
   Set<StockInResponse> getByDate(LocalDate startDate, LocalDate endDate);
   Page<StockInResponse> getByDatePage(LocalDate startDate, LocalDate endDate,Pageable pageable);
}
