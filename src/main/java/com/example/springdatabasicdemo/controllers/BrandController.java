package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddBrandDto;
import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addBrand() {return "brand-add";}

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {return new AddBrandDto();}

    @PostMapping("/create")
    public String createBrand(@Valid AddBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel", bindingResult);
            System.out.print("here is");
            return "redirect:/brands/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/brands/all";
    }

    @GetMapping("/all")
    public String showAllBrands(Model model) {
        model.addAttribute("brandList", brandService.getAllBrands());

        return "brand-all";
    }

    @DeleteMapping("/delete-brand/{brandName}")
    public void deleteBrandByName(@PathVariable String brandName) {
        brandService.deleteBrandByName(brandName);
    }

    @GetMapping("delete/id/{id}")
    public String deleteBrandById(@PathVariable("id") String brandId) {
        brandService.deleteBrandById(brandId);
        return "redirect:/brands/all";
    }
}
