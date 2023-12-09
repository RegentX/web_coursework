package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddModelDto;
import com.example.springdatabasicdemo.dtos.AddUserDto;
import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.services.OfferService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OfferService offerService;

    public UserController(RoleService roleService, UserService userService, OfferService offerService) {
        this.roleService = roleService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());
        return "user-add";
    }

    @ModelAttribute("userModel")
    public AddUserDto initUser() {return new AddUserDto();}

    @PostMapping("/create")
    public String createUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/add";
        }
        System.out.print(userModel.getImageUrl());
        System.out.print("---------");
        userService.registerUser(userModel);

        return "redirect:/users/all";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());

        return "user-all";
    }

    @GetMapping("/user-details/{user-name}")
    public String UserDetails(@PathVariable("user-name") String userName, Model model) {
        model.addAttribute("userDetails", userService.getUserByUsername(userName));
        model.addAttribute("offerDetails", offerService.getOffersBySeller(userName));
        return "user-detail";
    }

    @GetMapping("/user-edit/{userName}")
    public String editUserSend(@PathVariable String userName, Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());

        model.addAttribute("user", userService.getUserByUsername(userName));
        return "user-edit";
    }

    @PostMapping("/user-edit/{userName}")
    public String editUserSubmit(@PathVariable String userName, @Valid AddUserDto userDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("roleList", roleService.getAllRoles());

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);

            return "user-edit";
        }

        userService.editUser(userName, userDto);
        return "redirect:/users/all";
    }

    @GetMapping("/user/{userId}")
    public Optional<UserDto> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/user-role/{roleName}")
    public Optional<List<UserDto>> getUsersByRole(@PathVariable UserRole roleName) {
        return userService.getUsersByRole(roleName);
    }

    @PostMapping("/update/password/{userName}/{userPassword}")
    public String updateUserPassword(@PathVariable String userName, @PathVariable String userPassword) {
        userService.updateUserPassword(userPassword, userName);
        return "redirect:/users/all";
    }

    @PostMapping("/delete/user/{userName}")
    public String deleteUserByUserName(@PathVariable String userName) {
        userService.deleteUserByUserName(userName);
        return "redirect:/users/all";
    }

    @GetMapping("delete/id/{id}")
    public String deleteUserById(@PathVariable("id") String userId) {
        userService.deleteUserById(userId);
        return "redirect:/users/all";
    }
}
