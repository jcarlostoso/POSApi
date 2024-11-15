package com.jtorresDev.posAPI.repository;

import com.jtorresDev.posAPI.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity,Long> {
}
