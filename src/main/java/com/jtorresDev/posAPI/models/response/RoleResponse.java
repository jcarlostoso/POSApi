package com.jtorresDev.posAPI.models.response;

import com.jtorresDev.posAPI.util.enums.ERoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleResponse {
    private Long id;
    private ERoles name;
}
