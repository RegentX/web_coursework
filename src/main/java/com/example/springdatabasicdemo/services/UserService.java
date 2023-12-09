package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddUserDto;
import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.dtos.UserAdditionalInfoDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    UserAdditionalInfoDto getUserByUsername(String userName);

    void editUser(String userName, AddUserDto userDto);

    AddUserDto registerUser(AddUserDto user);

    Optional<UserDto> getUserById(String id);

    Optional<List<UserDto>> getUsersByRole(UserRole roleName);

    void updateUserPassword(String userPassword, String userName);

    void deleteUserById(String id);

    void deleteUserByUserName(String userName);
}
