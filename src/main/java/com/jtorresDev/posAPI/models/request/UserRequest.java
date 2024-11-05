package com.jtorresDev.posAPI.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest implements Serializable {

    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
   // private Set<String> roles;
    private Long idRol;

}
