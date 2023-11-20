package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.repositories.RoleRepository;
import com.example.springdatabasicdemo.services.RoleService;
import com.example.springdatabasicdemo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    ModelMapper modelMapper;

    RoleRepository roleRepository;

    ValidationUtil validationUtil;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {this.validationUtil = validationUtil; }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRole(RoleDto roleEntity) {
        if (!this.validationUtil.isValid(roleEntity)) {
            this.validationUtil
                    .violations(roleEntity)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                Role roleEx = modelMapper.map(roleEntity, Role.class);

                return modelMapper.map(roleRepository
                        .saveAndFlush(this.modelMapper.map(roleEntity, Role.class)), RoleDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return roleEntity;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map((c)->modelMapper.map(c, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(RoleDto role) {
        Role roleEx = modelMapper.map(role, Role.class);
        roleRepository.delete(roleEx);
    }

    @Override
    public void deleteRoleById(String id) {
        roleRepository.deleteRoleById(id);
    }
}
