package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.enums.Category;
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

    private ModelService modelService;

    @Autowired
    public void setService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/add")
    public String addModel() {return "model-add";}

    @ModelAttribute("modelModel")
    public ModelDto initModel() {return new ModelDto();}

    @PostMapping("/create")
    public String createModel(@Valid ModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

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

    @PostMapping("/model-update/image/{modelId}")
    public void addModelImage(@PathVariable String modelId, @RequestParam String imageUrl) {
        modelService.addModelImage(imageUrl, modelId);
    }

    @GetMapping("/delete/{modelId}")
    public String deleteModelById(@PathVariable("modelId") String modelId) {
        modelService.deleteModelById(modelId);
        return "redirect:/models/model/all";
    }

}
