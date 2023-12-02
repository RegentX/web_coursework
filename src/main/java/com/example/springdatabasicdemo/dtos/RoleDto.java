package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.BaseEntity;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDto {

    private UserRole roleName;

    public RoleDto() {}

    @NotNull(message="Role cannot be null. Please choose it!")
    public UserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRole roleName) {
        this.roleName = roleName;
    }

}
