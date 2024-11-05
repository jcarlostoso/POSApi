package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.util.enums.ETypeCustomer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeCustomerResponse implements Serializable {
    private Long id;
    private ETypeCustomer name;
}
