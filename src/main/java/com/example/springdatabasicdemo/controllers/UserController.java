package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.services.RoleService;
import com.example.springdatabasicdemo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addUser() {return "user-add";}

    @ModelAttribute("userModel")
    public UserDto initUser() {return new UserDto();}

    @PostMapping("/create")
    public String createUser(@Valid UserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/add";
        }
        userService.registerUser(userModel);

        return "redirect:/users/all";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());

        return "user-all";
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

    @GetMapping("delete/id/{id}")
    public String deleteUserById(@PathVariable("id") String userId) {
        userService.deleteUserById(userId);
        return "redirect:/users/all";
    }
}
