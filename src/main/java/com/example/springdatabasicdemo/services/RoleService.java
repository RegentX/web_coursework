package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto role);

    List<RoleDto> getAllRoles();

    void deleteRole(RoleDto role);

    void deleteRoleById(String id);
}
