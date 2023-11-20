package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add-brand")
    public BrandDto addBrand(@RequestBody BrandDto brandDto) {
        return brandService.addBrand(brandDto);
    }

    @GetMapping("/brand/all")
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @DeleteMapping("/delete-brand/{brandName}")
    public void deleteBrandByName(@PathVariable String brandName) {
        brandService.deleteBrandByName(brandName);
    }

    @DeleteMapping("delete-brand/id/{id}")
    public void deleteBrandById(@PathVariable String id) {
        brandService.deleteBrandById(id);
    }
}
