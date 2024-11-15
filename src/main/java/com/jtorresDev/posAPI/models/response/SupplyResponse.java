package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyResponse implements Serializable {
    private Long id;
    private String code;
    private String name;
    private BigDecimal price;
    private LocalDate registrationDate;
    private LocalDate modificationDate;

    private UserResponse user;
}
