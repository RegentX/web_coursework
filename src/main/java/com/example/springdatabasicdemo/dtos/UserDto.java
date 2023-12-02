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
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {

    private boolean isActive;

    private LocalDate created;

    private String firstName;

    private String imageUrl;

    private String lastName;

    private LocalDate modified;

    private String userPassword;

    private String username;

    private RoleDto role;

    public UserDto() {}

    @NotNull(message = "Please choose a status of user!")
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @NotNull(message = "Date of creation cannot be null or empty!")
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @NotEmpty
    @Length(min = 2, message = "First name must be more than 2 characters!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "Image url cannot be null or empty!")
    @Length(min = 10, max = 255, message = "URL of image must be more than 10 characters!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotEmpty
    @Length(min = 2, message = "Last name must be more than 2 characters!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "Date of modification cannot be null or empty!")
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @NotEmpty
    @Length(min = 8, message = "Password must be more than 8 characters!")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @NotEmpty
    @Length(min = 5, message = "User name must be more than 5 characters!")
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

}
