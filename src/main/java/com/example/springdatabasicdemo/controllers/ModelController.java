package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddModelDto;
import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.services.BrandService;
import com.example.springdatabasicdemo.services.ModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private final BrandService brandService;

    @Autowired
    private final ModelService modelService;
    public ModelController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }
// Why don't to use this ?
@GetMapping("/add")
public String addModel(Model model) {
    model.addAttribute("availableBrands", brandService.getAllBrands());

    return "model-add";}

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {return new AddModelDto();}


    @PostMapping("/create")
    public String createModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println(modelModel.getBrand());
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel", bindingResult);
            return "redirect:/models/add";
        }
        modelService.createNewModel(modelModel);

        return "redirect:/models/all";
    }

    @GetMapping("/all")
    public String showAllModels(Model model) {
        model.addAttribute("modelList", modelService.getAllModels());
        return "model-all";
    }

    @GetMapping("/{modelName}")
    public Optional<ModelDto> getModelWithUniqueName(@PathVariable String modelName) {
        return modelService.getModelWithUniqueName(modelName);
    }

    @GetMapping("/model/category/{category}")
    public Optional<List<ModelDto>> getModelsOfCategory(@PathVariable Category category) {
        return modelService.getModelsOfCategory(category);
    }

    @PostMapping("/model-update/end-year/{model-name}")
    public String updateModelEndYear(@PathVariable("model-name") String modelName, @Valid ModelDto modelDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("modelModel", modelDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel", result);
            return "redirect:/model-edit/" + modelName; // Adjust the redirect URL as necessary
        }
        modelService.updateModelEndYear(modelDto.getEndYear(), modelDto);
        return "redirect:/model-edit/" + modelName;
    }

    @GetMapping("/model-edit/{modelName}/{brandName}")
    public String editModelSend(@PathVariable String modelName, @PathVariable String brandName, Model model) {
        model.addAttribute("brandList", brandService.getAllBrands());

        model.addAttribute("model", modelService.getModelByBrandAndName(brandName, modelName));
        return "model-edit";
    }

    @PostMapping("/model-edit/{modelName}/{brandName}")
    public String editModelSubmit(@PathVariable String modelName, @PathVariable String brandName, @Valid AddModelDto modelDto, BindingResult result, Model model) {

        model.addAttribute("brandList", brandService.getAllBrands());
        if (result.hasErrors()) {
            model.addAttribute("model", modelDto);
            return "model-edit";
        }

        modelService.editModel(modelName, brandName, modelDto);
        return "redirect:/models/all";
    }

    @PostMapping("/model-update/image/{modelId}")
    public void addModelImage(@PathVariable String modelId, @RequestParam String imageUrl) {
        modelService.addModelImage(imageUrl, modelId);
    }

    @PostMapping("/delete/model/{brandName}/{modelName}")
    public String deleteModelByBrandAndName(@PathVariable String brandName, @PathVariable String modelName) {
        modelService.deleteModelByBrandAndName(brandName, modelName);
        return "redirect:/models/all";
    }

    @GetMapping("/model-details/{modelName}/{brandName}")
    public String ModelDetails(@PathVariable String modelName, @PathVariable String brandName, Model model) {
        model.addAttribute("modelDetails", modelService.getModelByBrandAndName(brandName, modelName));

        return "model-detail";
    }

    @GetMapping("/delete/{modelId}")
    public String deleteModelById(@PathVariable("modelId") String modelId) {
        modelService.deleteModelById(modelId);
        return "redirect:/models/all";
    }

}
