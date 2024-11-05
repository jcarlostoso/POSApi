package com.jtorresDev.posAPI.service;


import com.jtorresDev.posAPI.abstract_services.IUserService;

import com.jtorresDev.posAPI.entity.UserEntity;
import com.jtorresDev.posAPI.models.request.UserRequest;
import com.jtorresDev.posAPI.models.response.RoleResponse;
import com.jtorresDev.posAPI.models.response.UserResponse;
import com.jtorresDev.posAPI.repository.RoleRepository;
import com.jtorresDev.posAPI.repository.UserRepository;

import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import org.springframework.stereotype.Service;


import java.util.*;



@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

private  final UserRepository userRepository;
private final RoleRepository roleRepository;

    @Override
    public UserResponse create(UserRequest request) {

        var rol = roleRepository.findById(request.getIdRol()).orElseThrow();
        var userToPersist=UserEntity.builder()
                    .id(UUID.randomUUID())
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .role(rol)
                    .build();

            var userPersisted = userRepository.save(userToPersist);


        return this.entityToResponse(userPersisted);
    }

    @Override
    public UserResponse read(UUID id) {
        var user = this.userRepository.findById(id).orElseThrow();
        return this.entityToResponse(user);
    }

    @Override
    public Page<UserResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pageRequest).map(this::entityToResponse);
    }


    @Override
    public UserResponse update(UserRequest request, UUID uuid) {
        return null;
    }


    @Override
    public void delete(UUID id) {

    var userToDelete= userRepository.findById(id).orElseThrow();
    this.userRepository.delete(userToDelete);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var user=this.userRepository.findByUsername( username).orElseThrow();
//        return mapUserToUserDetails(user);
//    }

    private UserResponse entityToResponse(UserEntity entity){
        var response = new UserResponse();
        BeanUtils.copyProperties(entity,response);

        var rolResponse = new RoleResponse();
        BeanUtils.copyProperties(entity.getRole(),rolResponse);

        response.setIdRol(rolResponse);
        return  response;
    }
//    private static UserDetails mapUserToUserDetails(UserEntity user) {
//        Set<GrantedAuthority> roles=new HashSet<>();
//        roles.add(new SimpleGrantedAuthority(user.getRole()
//                .getName().name()));
//        Set<GrantedAuthority> authorities=new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole()
//                .getName().name()));
//
//        System.out.println("Authority from db" + authorities);
//        return new User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isEnabled(),
//                true,
//                true,
//                true,
//                authorities
//        );
//    }


}
