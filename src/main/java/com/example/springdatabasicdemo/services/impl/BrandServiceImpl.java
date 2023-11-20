package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.services.BrandService;
import com.example.springdatabasicdemo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService{
    ValidationUtil validationUtil;

    ModelMapper modelMapper;

    BrandRepository brandRepository;

//    @Autowired
//    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
//        this.modelMapper = modelMapper;
//        this.validationUtil = validationUtil;
//    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {this.validationUtil = validationUtil; }

    @Override
    public BrandDto addBrand(BrandDto brandEntity) {
        if (!this.validationUtil.isValid(brandEntity)) {
            this.validationUtil
                    .violations(brandEntity)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                Brand brandEx = modelMapper.map(brandEntity, Brand.class);

                    return modelMapper.map(brandRepository
                            .saveAndFlush(this.modelMapper.map(brandEntity, Brand.class)), BrandDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return brandEntity;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll().stream().map((c)->modelMapper.map(c,BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteBrandByName(String brandName) {
        brandRepository.deleteBrandByName(brandName);
    }

    @Override
    public void deleteBrandById(String id) {
        brandRepository.deleteBrandById(id);
    }
}
