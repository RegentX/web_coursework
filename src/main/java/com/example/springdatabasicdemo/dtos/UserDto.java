package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.BaseEntity;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {

    private String id;

    private boolean isActive;

    private LocalDate created;

    private String firstName;

    private String imageUrl;

    private String lastName;

    private LocalDate modified;

    private String userPassword;

    private String username;

    private RoleDto role;

    protected UserDto() {}

    public UserDto(String id, boolean isActive, String firstName, String imageUrl, String lastName, String userPassword, String userName, RoleDto role, LocalDate created, LocalDate modified) {
        this.isActive = isActive;
        this.firstName = firstName;
        this.imageUrl = imageUrl;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.username = userName;
        this.role = role;
        this.created = created;
        this.modified = modified;
        this.id = id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @NotNull
    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @NotNull
    @NotEmpty
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @NotNull
    @NotEmpty
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
