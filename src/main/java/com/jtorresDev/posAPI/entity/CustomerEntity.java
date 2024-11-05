package com.jtorresDev.posAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {
    @Id
    private UUID id;

    private String username;
    private String surnames;
    private String email;
    private String password;
    private boolean enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeCustomer_id")
    private TypeCustomerEntity type;

}
