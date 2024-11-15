package com.jtorresDev.posAPI.service;

import com.jtorresDev.posAPI.abstract_services.ISupplyService;
import com.jtorresDev.posAPI.entity.SupplyEntity;
import com.jtorresDev.posAPI.models.request.SupplyRequest;
import com.jtorresDev.posAPI.models.response.SupplyResponse;
import com.jtorresDev.posAPI.models.response.UserResponse;
import com.jtorresDev.posAPI.repository.SupplyRepository;
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
public class SupplyServiceImpl implements ISupplyService {
    private final SupplyRepository supplyRepository;
    private final UserRepository userRepository;

    @Override
    public SupplyResponse create(SupplyRequest request) {
        var user=this.userRepository.findById(request.getUser()).orElseThrow();
        var supplyToPersist= SupplyEntity.builder()
                .code(request.getCode())
                .name(request.getName())
                .price(request.getPrice())
                .registrationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .user(user)
                .build();
        var supplyPersisted= this.supplyRepository.save(supplyToPersist);
        return this.entityToResponse(supplyToPersist);
    }

    @Override
    public SupplyResponse read(Long id) {
        var supply= this.supplyRepository.findById(id).orElseThrow();

        return this.entityToResponse(supply);
    }

    @Override
    public Page<SupplyResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.supplyRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public SupplyResponse update(SupplyRequest request, Long id) {
        var supplyToUpdate =this.supplyRepository.findById(id).orElseThrow();
        var user=this.userRepository.findById(request.getUser()).orElseThrow();
        supplyToUpdate.setCode(request.getCode());
        supplyToUpdate.setName(request.getName());
        supplyToUpdate.setPrice(request.getPrice());
        supplyToUpdate.setUpdateDate(LocalDate.now());
        supplyToUpdate.setUser(user);
        var supplyUpdate=this.supplyRepository.save(supplyToUpdate);

        return this.entityToResponse(supplyUpdate);
    }

    @Override
    public void delete(Long id) {
        var supplyToDelete=this.supplyRepository.findById(id).orElseThrow();
        this.supplyRepository.delete(supplyToDelete);
    }

    private SupplyResponse entityToResponse (SupplyEntity entity){
        var response = new SupplyResponse();
        BeanUtils.copyProperties(entity,response);

        var userResponse = new UserResponse();
        BeanUtils.copyProperties(entity.getUser(),userResponse);

        response.setUser(userResponse);

        return response;
    }
}
