package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto registerUser(UserDto user);

    Optional<UserDto> getUserById(String id);

    Optional<List<UserDto>> getUsersByRole(UserRole roleName);

    void updateUserPassword(String userPassword, String id);

    void deleteUserById(String id);
}
