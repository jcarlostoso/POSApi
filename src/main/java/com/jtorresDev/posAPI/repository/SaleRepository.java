package com.jtorresDev.posAPI.repository;

import com.jtorresDev.posAPI.entity.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<SaleEntity,Long> {
    @Query("select f from SaleEntity  f where f.date between :startDate and :endDate")
    Page<SaleEntity> salesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
