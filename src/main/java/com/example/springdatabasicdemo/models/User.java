package com.example.springdatabasicdemo.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import jdk.jfr.Category;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private boolean isActive;

    private LocalDate created;

    private String firstName;

    private String imageUrl;

    private String lastName;

    private LocalDate modified;

    private String userPassword;

    private String username;

    private Role role;

    private List<Offer> offers;

    protected User() {}

    public User(boolean isActive, LocalDate created, String firstName, String imageUrl, String lastName, LocalDate modified, String userPassword, String userName, Role role, List<Offer> offers) {
        this.isActive = isActive;
        this.created = created;
        this.firstName = firstName;
        this.imageUrl = imageUrl;
        this.lastName = lastName;
        this.modified = modified;
        this.userPassword = userPassword;
        this.username = userName;
        this.role = role;
        this.offers = offers;
    }

    @Column(name = "is_active", nullable = false)
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "modified", nullable = false)
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @Column(name = "user_password", nullable = false)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}

