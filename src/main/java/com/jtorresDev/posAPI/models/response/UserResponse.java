package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.entity.RoleEntity;
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
public class UserResponse implements Serializable {
    private UUID id;
    private String email;
    private String username;
    private String password;

    private RoleResponse idRol;

}
