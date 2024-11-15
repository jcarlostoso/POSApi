package com.jtorresDev.posAPI.service;

import com.jtorresDev.posAPI.abstract_services.IRoleService;
import com.jtorresDev.posAPI.entity.RoleEntity;
import com.jtorresDev.posAPI.models.request.RoleRequest;
import com.jtorresDev.posAPI.models.response.RoleResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements IRoleService {
    @Override
    public RoleResponse create(RoleRequest request) {
        return null;
    }

    @Override
    public RoleResponse read(Long aLong) {
        return null;
    }

    @Override
    public Page<RoleResponse> realAll(Integer page, Integer size, SortType sortType) {
        return null;
    }

    @Override
    public RoleResponse update(RoleRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    private  RoleResponse entityToResponse(RoleEntity entity){
        var response = new RoleResponse();
        BeanUtils.copyProperties(entity,response);

        return response;
    }
}
