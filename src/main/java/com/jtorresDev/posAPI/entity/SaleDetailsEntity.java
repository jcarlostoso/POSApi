package com.jtorresDev.posAPI.entity;

import com.jtorresDev.posAPI.models.request.SaleRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="saleDetails")
public class SaleDetailsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;


    private Double pieces;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;


}
