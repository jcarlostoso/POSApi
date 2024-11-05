package com.jtorresDev.posAPI.abstract_services;

import com.jtorresDev.posAPI.models.request.CustomerRequest;
import com.jtorresDev.posAPI.models.response.CustomerResponse;

import java.util.UUID;

public interface ICustomerService extends ICrudService<CustomerRequest, CustomerResponse, UUID>{
    CustomerResponse disableCustomer(UUID id);
    CustomerResponse enableCustomer(UUID id);
}
