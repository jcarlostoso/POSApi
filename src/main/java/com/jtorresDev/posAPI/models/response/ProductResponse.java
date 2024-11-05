package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal costPrice;
    private BigDecimal price;
    private Double stockMin;
    private LocalDate registrationDate;
    private LocalDate updatedDate;

    private UserResponse idUser;
}
