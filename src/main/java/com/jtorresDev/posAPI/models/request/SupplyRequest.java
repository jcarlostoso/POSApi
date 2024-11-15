package com.jtorresDev.posAPI.models.request;

import com.jtorresDev.posAPI.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyRequest implements Serializable {

    private String code;
    private String name;
    private BigDecimal price;

    private UUID user;
}
