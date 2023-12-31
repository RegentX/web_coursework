package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findRoleById(String id);

    Role findRoleByRoleName(UserRole role);

    Role deleteRoleById(String id);

    Role deleteRoleByRoleName(Role role);

    void deleteRoleByRoleName(UserRole roleName);

}

