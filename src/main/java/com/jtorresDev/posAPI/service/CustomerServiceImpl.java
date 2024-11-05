package com.jtorresDev.posAPI.service;

import com.jtorresDev.posAPI.abstract_services.ICustomerService;
import com.jtorresDev.posAPI.entity.CustomerEntity;
import com.jtorresDev.posAPI.models.request.CustomerRequest;
import com.jtorresDev.posAPI.models.response.CustomerResponse;
import com.jtorresDev.posAPI.models.response.TypeCustomerResponse;

import com.jtorresDev.posAPI.repository.CustomerRepository;
import com.jtorresDev.posAPI.repository.TypeCustomerRepository;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final TypeCustomerRepository typeCustomerRepository;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        var type = typeCustomerRepository.findById(request.getIdType()).orElseThrow();
        var customerToPersist = CustomerEntity.builder()
                .id(UUID.randomUUID())
                .username(request.getUsername())
                .surnames(request.getSurnames())
                .email(request.getEmail())
                .password(request.getPassword())
                .enabled(true)
                .type(type)
                .build();
        var customerPersisted = this.customerRepository.save(customerToPersist);
        return this.entityToResponse(customerPersisted);
    }

    @Override
    public CustomerResponse read(UUID id) {
        var customer = this.customerRepository.findById(id).orElseThrow();

        return this.entityToResponse(customer);
    }

    @Override
    public Page<CustomerResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.customerRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public CustomerResponse update(CustomerRequest request, UUID id) {
        var customerToUpdate = this.customerRepository.findById(id).orElseThrow();
        var typeCustomer = this.typeCustomerRepository.findById(request.getIdType()).orElseThrow();
        customerToUpdate.setUsername(request.getUsername());
        customerToUpdate.setSurnames(request.getSurnames());
        customerToUpdate.setEmail(request.getEmail());
        customerToUpdate.setPassword(request.getPassword());
        customerToUpdate.setType(typeCustomer);
        var customerUpdated =this.customerRepository.save(customerToUpdate);
        return this.entityToResponse(customerUpdated);
    }
    @Override
    public CustomerResponse disableCustomer(UUID id) {
        var customerToUpdate = this.customerRepository.findById(id).orElseThrow();
        customerToUpdate.setEnabled(false);
        var customerUpdated = this.customerRepository.save(customerToUpdate);
        return this.entityToResponse(customerUpdated);
    }
    @Override
    public CustomerResponse enableCustomer(UUID id) {
        var customerToUpdate = this.customerRepository.findById(id).orElseThrow();
        customerToUpdate.setEnabled(true);
        var customerUpdated = this.customerRepository.save(customerToUpdate);
        return this.entityToResponse(customerUpdated);
    }
    @Override
    public void delete(UUID id) {
        var customerToDelete = this.customerRepository.findById(id).orElseThrow();
        this.customerRepository.delete(customerToDelete);
    }


    private CustomerResponse entityToResponse(CustomerEntity entity){
        var response =new CustomerResponse();
        BeanUtils.copyProperties(entity,response);

        var typeCustomerResponse =new TypeCustomerResponse();
        BeanUtils.copyProperties(entity.getType(),typeCustomerResponse);

        response.setIdType(typeCustomerResponse);

        return response;
    }


}
