package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.RoleDto;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.repositories.RoleRepository;
import com.example.springdatabasicdemo.services.RoleService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    ModelMapper modelMapper;

    RoleRepository roleRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRole(RoleDto roleEntity) {
            try {
                Role roleEx = modelMapper.map(roleEntity, Role.class);
                roleEx.setCreated(LocalDateTime.now());
                roleEx.setModified(LocalDateTime.now());
                return modelMapper.map(roleRepository
                        .saveAndFlush(this.modelMapper.map(roleEx, Role.class)), RoleDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
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

    @Transactional
    @Override
    public void deleteRoleByRoleName(UserRole roleName) {
        roleRepository.deleteRoleByRoleName(roleName);

    }
}
