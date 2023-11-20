package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create-role")
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }

    @GetMapping("/role/all")
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/delete-role/role")
    public void deleteRole(@RequestBody RoleDto roleDto) {
        roleService.deleteRole(roleDto);
    }

    @DeleteMapping("/delete-role/{roleId}")
    public void deleteRoleById(@PathVariable String roleId) {
        roleService.deleteRoleById(roleId);
    }
}
