package com.jtorresDev.posAPI.service;

import com.jtorresDev.posAPI.abstract_services.ISaleService;
import com.jtorresDev.posAPI.entity.*;
import com.jtorresDev.posAPI.models.request.SaleDetailsRequest;
import com.jtorresDev.posAPI.models.request.SaleRequest;

import com.jtorresDev.posAPI.models.response.*;
import com.jtorresDev.posAPI.repository.CustomerRepository;
import com.jtorresDev.posAPI.repository.ProductRepository;
import com.jtorresDev.posAPI.repository.SaleRepository;
import com.jtorresDev.posAPI.repository.UserRepository;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


@Service
@AllArgsConstructor
@Transactional
public class SaleServiceImpl implements ISaleService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

@Override
public SaleResponse create(SaleRequest request) {
    System.out.println("mi request en sale server");
    System.out.println(request);
    var user = userRepository.findById(request.getUser()).orElseThrow();
    var customer = customerRepository.findById(request.getCustomer()).orElseThrow();

    var saleToPersist = SaleEntity.builder()
            .date(LocalDate.now())
            .typeSale(request.getTypeSale())
            .statusSale(request.getStatusSale())
            .deliveryDate(request.getDeliveryDate())
            .paymentMethod(request.getPaymentMethod())
            .customer(customer)
            .user(user)
            .build();

    List<SaleDetailsEntity> saleDetailsEntities = new ArrayList<>();
    for (SaleDetailsRequest detailRequest : request.getSaleDetail()) {
        var product = productRepository.findById(detailRequest.getProduct()).orElseThrow();

        var saleDetail = new SaleDetailsEntity();
        saleDetail.setProduct(product);
        saleDetail.setPieces(detailRequest.getPieces());
        saleDetail.setPrice(detailRequest.getPrice());
        saleDetail.setSale(saleToPersist);
        saleDetailsEntities.add(saleDetail);
    }

    saleToPersist.setSaleDetail(saleDetailsEntities);
    var salePersisted = saleRepository.save(saleToPersist);

    return entityToResponse(salePersisted);
}

    @Override
    public SaleResponse read(Long id) {
        var saleToRead = this.saleRepository.findById(id).orElseThrow();
        return this.entityToResponse(saleToRead);
    }

    @Override
    public Page<SaleResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.saleRepository.findAll(pageRequest).map(this::entityToResponse);
    }
    @Override
    public Page<SaleResponse> salesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable, SortType sortType) {
        PageRequest pageRequest=null;
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        switch ( sortType) {
            case NONE -> pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            case LOWER -> pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("date").ascending());
            case UPPER -> pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("date").descending());
        }

        return this.saleRepository.salesByDateRange(startDate,endDate,pageable).map(this::entityToResponse);
    }

    @Override
    public SaleResponse update(SaleRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
    //personalizando los datos que se devolveran por respuesta en las ventas
    /*
    * Para usar esto se cambiaron cosas de SalesDetailsresponse*/
    private Map<String, Object> transformProductResponse(ProductResponse productResponse) {
    Map<String, Object> customizedProductResponse = new HashMap<>();
    customizedProductResponse.put("id", productResponse.getId());
    customizedProductResponse.put("name", productResponse.getName());
    //customizedProductResponse.put("customField", "customValue");
        // Añade campos personalizados aquí
        // Añade otros campos o transforma datos según sea necesario
        return customizedProductResponse; }

    private SaleResponse entityToResponse(SaleEntity entity) {
        var response = new SaleResponse();
        BeanUtils.copyProperties(entity, response);

        var userResponse = new UserResponse();
        BeanUtils.copyProperties(entity.getUser(), userResponse);
        response.setUser(userResponse);

        var customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(entity.getCustomer(), customerResponse);
        response.setCustomer(customerResponse);

        List<SaleDetailsResponse> saleDetailsResponses = new ArrayList<>();
        for (SaleDetailsEntity detail : entity.getSaleDetail()) {
            var detailResponse = new SaleDetailsResponse();
            BeanUtils.copyProperties(detail, detailResponse);
            /*
            //manda todo el reponse de producto
            // tambien cambia el SalesDetailResponse
            var productResponse = new ProductResponse();
            BeanUtils.copyProperties(detail.getProduct(), productResponse);
            detailResponse.setProduct(productResponse);
             */
            //manda el response modificado de producto
            var productResponse = new ProductResponse(); BeanUtils.copyProperties(detail.getProduct(), productResponse);
            var customizedProductResponse = transformProductResponse(productResponse);
            detailResponse.setProduct(customizedProductResponse);

            var saleResponse = new SaleResponse();
            BeanUtils.copyProperties(detail.getSale(), saleResponse);
            detailResponse.setSale(saleResponse);

            saleDetailsResponses.add(detailResponse);
        }
        response.setSaleDetails(saleDetailsResponses);

        return response;
    }


    private SaleResponse entityToResponseOLD (SaleEntity entity) {
        var response = new SaleResponse();
        BeanUtils.copyProperties(entity, response);

        var userResponse = new UserResponse();
        BeanUtils.copyProperties(entity.getUser(), userResponse);
        response.setUser(userResponse);

        var customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(entity.getCustomer(), customerResponse);
        response.setCustomer(customerResponse);

        List<SaleDetailsResponse> saleDetailsResponses = new ArrayList<>();
        for (SaleDetailsEntity detail : entity.getSaleDetail()) {
            var detailResponse = new SaleDetailsResponse();
            BeanUtils.copyProperties(detail, detailResponse);
            saleDetailsResponses.add(detailResponse);
        }
        response.setSaleDetails(saleDetailsResponses);

        return response;
    }


}
