package com.example.springdatabasicdemo.services.impl;

import ch.qos.logback.core.model.Model;
import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.RoleRepository;
import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.UserService;
import com.example.springdatabasicdemo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    ModelMapper modelMapper;

    UserRepository userRepository;

    ValidationUtil validationUtil;

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {this.validationUtil = validationUtil;}

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto registerUser(UserDto userEntity) {
        if (!this.validationUtil.isValid(userEntity)) {
            this.validationUtil
                    .violations(userEntity)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                User userEx = modelMapper.map(userEntity, User.class);

                return modelMapper.map(userRepository
                        .saveAndFlush(this.modelMapper.map(userEntity, User.class)), UserDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

        return userEntity;
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
    public void updateUserPassword(String userPassword, String id) {
        Optional<User> userEx = Optional.ofNullable(userRepository.findUserById(id));
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
}
