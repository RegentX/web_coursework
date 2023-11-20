package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.models.Brand;

import java.util.Date;
import java.util.List;

public interface BrandService {

    BrandDto addBrand(BrandDto brand);

    List<BrandDto> getAllBrands();

    void deleteBrandByName(String brandName);

    void deleteBrandById(String id);

}

