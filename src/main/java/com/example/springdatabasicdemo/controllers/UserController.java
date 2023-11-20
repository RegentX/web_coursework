package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @GetMapping("/user/{userId}")
    public Optional<UserDto> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/user-role/{roleName}")
    public Optional<List<UserDto>> getUsersByRole(@PathVariable UserRole roleName) {
        return userService.getUsersByRole(roleName);
    }

    @PutMapping("/{userId}/password")
    public void updateUserPassword(@PathVariable String userId, @RequestParam String userPassword) {
        userService.updateUserPassword(userPassword, userId);
    }

    @DeleteMapping("/delete-user/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }
}
