package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.services.OfferService;
import com.example.springdatabasicdemo.services.RoleService;
import jakarta.validation.Valid;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/add")
    public String addRole() {return "role-add";}

    @ModelAttribute("roleModel")
    public RoleDto initRole() {return new RoleDto();}

    @PostMapping("/create")
    public String createRole(@Valid RoleDto roleModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("roleModel", roleModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.roleModel", bindingResult);
            return "redirect:/roles/add";
        }
        roleService.createRole(roleModel);

        return "redirect:/roles/all";
    }

    @GetMapping("/all")
    public String showAllOffers(Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());

        return "role-all";
    }

    @DeleteMapping("/delete-role/role")
    public void deleteRole(@RequestBody RoleDto roleDto) {
        roleService.deleteRole(roleDto);
    }

    @GetMapping("delete/id/{id}")
    public String deleteRoleById(@PathVariable("id") String roleId) {
        roleService.deleteRoleById(roleId);
        return "redirect:/roles/all";
    }
}
