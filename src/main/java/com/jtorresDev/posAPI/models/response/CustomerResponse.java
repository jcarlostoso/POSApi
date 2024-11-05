package com.jtorresDev.posAPI.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse implements Serializable {
    private UUID id;
    private String username;
    private String surnames;
    private String email;
    private String password;
    private boolean enabled;
    private TypeCustomerResponse idType;
}
