package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.entity.ProductEntity;
import com.jtorresDev.posAPI.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StockInResponse {
    private Long id;
    private Double amount;
    private LocalDate date;

    private ProductResponse product;

    private UserResponse user;
}
