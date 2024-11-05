package com.jtorresDev.posAPI.models.request;

import com.jtorresDev.posAPI.util.enums.ERoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleRequest implements Serializable {

    private Long id;
    private ERoles name;
}
