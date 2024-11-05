package com.jtorresDev.posAPI.service;

import com.jtorresDev.posAPI.abstract_services.IProductsService;
import com.jtorresDev.posAPI.entity.ProductEntity;
import com.jtorresDev.posAPI.models.request.ProductRequest;
import com.jtorresDev.posAPI.models.response.ProductResponse;
import com.jtorresDev.posAPI.models.response.UserResponse;
import com.jtorresDev.posAPI.repository.ProductRepository;
import com.jtorresDev.posAPI.repository.UserRepository;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductsService {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        var  user= userRepository.findById(request.getIdUser()).orElseThrow();
        var productToPersist = ProductEntity.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .costPrice(request.getCostPrice())
                .price(request.getPrice())
                .stockMin(request.getStockMin())
                .registrationDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .idUser(user)
                .build();
        var productPersisted = this.productRepository.save(productToPersist);

        return this.entityToResponse(productPersisted);
    }

    @Override
    public ProductResponse read(Long id) {
        var product = this.productRepository.findById(id).orElseThrow();
        return this.entityToResponse(product);
    }

    @Override
    public Page<ProductResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.productRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public ProductResponse update(ProductRequest request, Long id) {
        var productToUpdate=this.productRepository.findById(id).orElseThrow();
        var  user= userRepository.findById(request.getIdUser()).orElseThrow();
        productToUpdate.setCode(request.getCode());
        productToUpdate.setName(request.getName());
        productToUpdate.setDescription(request.getDescription());
        productToUpdate.setCostPrice(request.getCostPrice());
        productToUpdate.setPrice(request.getPrice());
        productToUpdate.setStockMin(request.getStockMin());
        productToUpdate.setUpdatedDate(LocalDate.now());
        productToUpdate.setIdUser(user);
        return this.entityToResponse(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        var productToDelete= this.productRepository.findById(id).orElseThrow();
        this.productRepository.delete(productToDelete);
    }

    private ProductResponse entityToResponse (ProductEntity entity){
        var response = new ProductResponse();
        BeanUtils.copyProperties(entity,response);

        var userResponse =new UserResponse();
        BeanUtils.copyProperties(entity.getIdUser(),userResponse);

        response.setIdUser(userResponse);
        return response;
    }
}
