package com.jtorresDev.posAPI.models.request;

import com.jtorresDev.posAPI.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleDetailsRequest implements Serializable {

    private Long product;
    private Double pieces;
    private BigDecimal price;
    private SaleRequest sale;
}
