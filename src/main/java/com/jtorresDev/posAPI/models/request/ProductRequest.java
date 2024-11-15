package com.jtorresDev.posAPI.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest implements Serializable {
    @NotBlank
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    private String description;
    @Positive
    @NotNull
    private BigDecimal costPrice;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    private Double stockMin;

    private UUID idUser;
    private Long idProductType;
}
