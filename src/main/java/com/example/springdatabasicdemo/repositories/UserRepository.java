package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserById(String id);

    User findUserByUsername(String userName);

    List<User> findUsersByIsActive(Boolean isActive);

    List<User> findUsersByFirstName(String firstName);

    List<User> findUsersByLastName(String lastName);

    List<User> findUsersByRole(UserRole role);

    void deleteUserByUsername(String userName);

    User deleteUserById(String id);

    User deleteUserByFirstNameAndLastName(String firstName, String lastName);
}


