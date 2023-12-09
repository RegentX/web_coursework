package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddBrandDto;
import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.models.Brand;

import java.util.Date;
import java.util.List;

public interface BrandService {

    AddBrandDto addBrand(AddBrandDto brand);

    List<AddBrandDto> getAllBrands();

    void deleteBrandByName(String brandName);

    void deleteBrandById(String id);

}

