package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {

    private ModelService modelService;

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ModelDto createNewModel(@RequestBody ModelDto modelDto) {
        return modelService.createNewModel(modelDto);
    }

    @GetMapping("model/by-brand")
    public Optional<List<ModelDto>> getModelsOfUniqueBrand(@RequestBody BrandDto brand) {
        // Assuming you pass the brand ID in the request path variable.
        // You can change the variable name if it's different.
        return modelService.getModelsOfUniqueBrand(brand);
    }

    @GetMapping("/{modelName}")
    public Optional<ModelDto> getModelWithUniqueName(@PathVariable String modelName) {
        return modelService.getModelWithUniqueName(modelName);
    }

    @GetMapping("/model/category/{category}")
    public Optional<List<ModelDto>> getModelsOfCategory(@PathVariable Category category) {
        return modelService.getModelsOfCategory(category);
    }

    @PostMapping("/model-update/image/{modelId}")
    public void addModelImage(@PathVariable String modelId, @RequestParam String imageUrl) {
        modelService.addModelImage(imageUrl, modelId);
    }

    @DeleteMapping("/delete-model/{modelId}")
    public void deleteModelById(@PathVariable String modelId) {
        modelService.deleteModelById(modelId);
    }

}
