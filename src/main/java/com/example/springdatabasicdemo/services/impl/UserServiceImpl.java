package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.RoleRepository;
import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.UserService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    ModelMapper modelMapper;

    UserRepository userRepository;

    RoleRepository roleRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(model -> {
            UserDto userDto = modelMapper.map(model, UserDto.class);
            if (model.getRole() != null) {
                userDto.setRole(model.getRole().getRoleName());
            }
            System.out.println(userDto.getRole());
            return userDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserAdditionalInfoDto getUserByUsername(String userName) {
        return modelMapper.map(userRepository.findUserByUsername(userName), UserAdditionalInfoDto.class);
    }

    @Override
    public AddUserDto registerUser(AddUserDto userEntity) {
            try {
                Role role = roleRepository.findRoleByRoleName(userEntity.getRole());
                User userEx = modelMapper.map(userEntity, User.class);
//                String roleId = role.getId();
                userEx.setRole(role);
                userEx.setCreated(LocalDateTime.now());
                userEx.setModified(LocalDateTime.now());
                userEx.setIsActive(true);
                return modelMapper.map(userRepository
                        .saveAndFlush(this.modelMapper.map(userEx, User.class)), AddUserDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!" + e);
            }
        return userEntity;
    }

    @Override
    public void editUser(String userName, AddUserDto userDto) {
        User user = userRepository.findUserByUsername(userName);
        System.out.println(userDto.getRole());
        System.out.println("------");
        Role role = roleRepository.findRoleByRoleName(userDto.getRole());

        user.setRole(role);
        user.setUserPassword(userDto.getUserPassword());
        user.setModified(LocalDateTime.now());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setImageUrl(userDto.getImageUrl());

        userRepository.saveAndFlush(user);

    }

    @Override
    public Optional<UserDto> getUserById(String id) {
        Optional<User> userEx = Optional.ofNullable(userRepository.findUserById(id));
        if (userEx.isPresent()) {
            return Optional.of(
            modelMapper.map(userEx, UserDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<UserDto>> getUsersByRole(UserRole roleName) {
        List<User> users = userRepository.findUsersByRole(roleName);
        List<UserDto> userDtos = null;
        for  (User user : users) {
            userDtos.add(modelMapper.map(user, UserDto.class));
        }
        return Optional.ofNullable(userDtos);
    }

    @Override
    public void updateUserPassword(String userPassword, String userName) {
        Optional<User> userEx = Optional.ofNullable(userRepository.findUserByUsername(userName));
        if (userEx.isPresent()) {
            User user = userEx.get();
            user.setUserPassword(userPassword);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }

    @Transactional
    @Override
    public void deleteUserByUserName(String userName) {
        userRepository.deleteUserByUsername(userName);
    }
}
