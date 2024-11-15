package com.jtorresDev.posAPI.service;

import com.jtorresDev.posAPI.abstract_services.IStockInService;
import com.jtorresDev.posAPI.entity.StockInEntity;
import com.jtorresDev.posAPI.models.request.StockInRequest;
import com.jtorresDev.posAPI.models.response.ProductResponse;
import com.jtorresDev.posAPI.models.response.StockInResponse;
import com.jtorresDev.posAPI.models.response.UserResponse;
import com.jtorresDev.posAPI.repository.ProductRepository;
import com.jtorresDev.posAPI.repository.StockInRepository;
import com.jtorresDev.posAPI.repository.UserRepository;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockInServiceImpl implements IStockInService {

    private final StockInRepository stockInRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public StockInResponse create(StockInRequest request) {
       // var user = userRepository.findById(request.getUser().getId()).orElseThrow();
        var user = userRepository.findById(request.getUser()).orElseThrow();
       // var product = productRepository.findById(request.getIdProduct().getId()).orElseThrow();
        var product = productRepository.findById(request.getIdProduct()).orElseThrow();
        var stockToPersist = StockInEntity.builder()
                .amount(request.getAmount())
                .date(LocalDate.now())
                .product(product)
                .user(user)
                .build();
        var stockPersisted = this.stockInRepository.save(stockToPersist);
        return this.entityToResponse(stockPersisted);
    }

    @Override
    public StockInResponse read(Long id) {
        var stock =this.stockInRepository.findById(id).orElseThrow();
        return this.entityToResponse(stock);
    }

    @Override
    public Page<StockInResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.stockInRepository.findAll(pageRequest).map(this::entityToResponse);
    }

     @Override
    public Set<StockInResponse> getByDate(LocalDate startDate, LocalDate endDate) {
        return this.stockInRepository.getByDate(startDate,endDate).stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    public Page<StockInResponse> getByDatePage(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return this.stockInRepository.getByDatePage(startDate,endDate,pageable).map(this::entityToResponse);
    }

    @Override
    public StockInResponse update(StockInRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long id) {
        var stockinToDelete=this.stockInRepository.findById(id).orElseThrow();
        this.stockInRepository.delete(stockinToDelete);

    }







    private StockInResponse entityToResponse (StockInEntity entity){
        var response = new StockInResponse();
        BeanUtils.copyProperties(entity,response);

        var userResponse =new UserResponse();
        BeanUtils.copyProperties(entity.getUser(),userResponse);
        response.setUser(userResponse);

        var productResponse = new ProductResponse();
        BeanUtils.copyProperties(entity.getProduct(),productResponse);
        response.setProduct(productResponse);
        return response;
    }



}
