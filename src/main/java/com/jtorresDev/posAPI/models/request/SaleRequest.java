package com.jtorresDev.posAPI.models.request;

import com.jtorresDev.posAPI.entity.CustomerEntity;
import com.jtorresDev.posAPI.entity.SaleDetailsEntity;
import com.jtorresDev.posAPI.entity.UserEntity;
import com.jtorresDev.posAPI.models.response.SaleDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleRequest implements Serializable {
    //private Long id;

    //private LocalDate date;
    private int typeSale;
    private int statusSale;
    private LocalDate deliveryDate;
    private int paymentMethod;

    private UUID customer;
    private UUID user;
    private List<SaleDetailsRequest> saleDetail;
}
