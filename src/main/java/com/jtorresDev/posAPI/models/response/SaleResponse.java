package com.jtorresDev.posAPI.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleResponse implements Serializable {
    private Long id;
    private LocalDate date;
    private int typeSale;
    private int statusSale;
    private LocalDate deliveryDate;
    private int paymentMethod;
    private List<SaleDetailsResponse> saleDetails;
    private CustomerResponse customer;
    private UserResponse user;
}
