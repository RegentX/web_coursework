package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.UserRole;
import jakarta.persistence.*;
import jdk.jfr.Category;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private UserRole roleName;

    private List<User> users;

    protected Role() {}

    public Role(UserRole roleName) {
        this.roleName = roleName;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_name", nullable = false)
    public UserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRole roleName) {
        this.roleName = roleName;
    }

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

