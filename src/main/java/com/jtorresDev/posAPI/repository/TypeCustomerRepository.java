package com.jtorresDev.posAPI.repository;

import com.jtorresDev.posAPI.entity.TypeCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCustomerRepository extends JpaRepository<TypeCustomerEntity,Long> {
}
