package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "roles")
@AllArgsConstructor
public class RoleController {
    private final IRoleService RoleService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Roles";
    }
}
