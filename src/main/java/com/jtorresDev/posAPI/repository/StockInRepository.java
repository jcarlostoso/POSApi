package com.jtorresDev.posAPI.repository;

import com.jtorresDev.posAPI.entity.StockInEntity;

import com.jtorresDev.posAPI.models.response.StockInResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface StockInRepository extends JpaRepository<StockInEntity,Long> {
    //Page<StockInResponse> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Query("select f from StockInEntity  f where f.date between :startDate and :endDate")
    Set<StockInEntity> getByDate(LocalDate startDate, LocalDate endDate);
    @Query("select f from StockInEntity  f where f.date between :startDate and :endDate")
    Page<StockInEntity>getByDatePage(LocalDate startDate, LocalDate endDate,Pageable pageable);
}
   // Page<StockInEntity> findByStartDateBetween(LocalDate dateStar, LocalDate dateEnd);


