package com.jtorresDev.posAPI.abstract_services;

import com.jtorresDev.posAPI.entity.UserEntity;
import com.jtorresDev.posAPI.models.request.UserRequest;
import com.jtorresDev.posAPI.models.response.UserResponse;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IUserService extends ICrudService<UserRequest, UserResponse, UUID>{


}
