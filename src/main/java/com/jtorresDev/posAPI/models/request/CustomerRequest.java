package com.jtorresDev.posAPI.models.request;

import com.jtorresDev.posAPI.entity.TypeCustomerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String surnames;
    @Email
    private String email;
    @Size(min = 6 , max = 12)
    private String password;
    private Long idType;
}
