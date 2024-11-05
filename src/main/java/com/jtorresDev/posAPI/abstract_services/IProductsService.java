package com.jtorresDev.posAPI.abstract_services;

import com.jtorresDev.posAPI.models.request.ProductRequest;
import com.jtorresDev.posAPI.models.response.ProductResponse;

public interface IProductsService extends ICrudService<ProductRequest, ProductResponse,Long>{
}
