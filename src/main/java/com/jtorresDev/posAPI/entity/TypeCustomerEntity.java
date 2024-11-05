package com.jtorresDev.posAPI.entity;

import com.jtorresDev.posAPI.util.enums.ETypeCustomer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "type_customer")
public class TypeCustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ETypeCustomer name;

    @OneToMany(mappedBy = "type")
    private Set<CustomerEntity> customer;

}
