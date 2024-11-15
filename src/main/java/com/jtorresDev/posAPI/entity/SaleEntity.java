package com.jtorresDev.posAPI.entity;

import com.jtorresDev.posAPI.models.request.SaleDetailsRequest;
import com.jtorresDev.posAPI.models.response.SaleDetailsResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sales")
public class SaleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int typeSale;
    private int statusSale;
    private LocalDate deliveryDate;
    private int paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

   // @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetailsEntity> saleDetail =new ArrayList<>();

}
