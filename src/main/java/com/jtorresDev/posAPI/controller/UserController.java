package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.IUserService;
import com.jtorresDev.posAPI.models.request.UserRequest;
import com.jtorresDev.posAPI.models.response.UserResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor

public class UserController {

    private final IUserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.read(id));
    }
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response =this.userService.realAll(page,size,sortType);
        return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(response);
    }

    @PostMapping("createUser")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request){

        return ResponseEntity.ok(userService.create(request));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
