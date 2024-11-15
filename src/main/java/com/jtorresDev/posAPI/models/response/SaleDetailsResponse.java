package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.entity.ProductEntity;
import com.jtorresDev.posAPI.models.request.SaleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleDetailsResponse implements Serializable {
    private Long id;
    private SaleResponse sale;
    /*si se usa el response personalizado se debe desabiliar */
    //private ProductResponse product;
    private Map<String, Object> product;
    private Double pieces;
    private BigDecimal price;


    public void setSale(SaleResponse sale) { this.sale = sale; }

    /*si se usa el response personalizado se debe desabiliar */
    //public void setProduct(ProductResponse product) { this.product = product; }

    public void setProduct(Map<String, Object> product) { this.product = product;}
}
