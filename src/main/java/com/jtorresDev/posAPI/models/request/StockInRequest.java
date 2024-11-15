package com.jtorresDev.posAPI.models.request;

import com.jtorresDev.posAPI.entity.ProductEntity;
import com.jtorresDev.posAPI.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StockInRequest implements Serializable {
    @NotNull
    @Positive
    private Double amount;

    private Long idProduct;

    private UUID user;


}
