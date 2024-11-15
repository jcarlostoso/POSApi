package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.ICustomerService;
import com.jtorresDev.posAPI.entity.CustomerEntity;
import com.jtorresDev.posAPI.models.request.CustomerRequest;
import com.jtorresDev.posAPI.models.response.CustomerResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
@RestController
@RequestMapping(path = "customer")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello Customer");
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType
    ) {
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = this.customerService.realAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.read(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.create(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@Valid @PathVariable UUID id,
            @Valid @RequestBody CustomerRequest request){
        return ResponseEntity.ok(this.customerService.update(request,id));

    }
    @PutMapping("/enable/{id}")
    public ResponseEntity<CustomerResponse> enableCustomer(@PathVariable UUID id){
        return ResponseEntity.ok(this.customerService.enableCustomer(id));
    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<CustomerResponse> disableCustomer(@PathVariable UUID id){
        return ResponseEntity.ok(this.customerService.disableCustomer(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteCustomer(@PathVariable UUID id){
        this.customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}