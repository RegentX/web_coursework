package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddModelDto;
import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelAdditionalInfoDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.enums.Category;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    List<ModelDto> getAllModels();

    AddModelDto createNewModel(AddModelDto model);

    ModelAdditionalInfoDto getModelByBrandAndName(String brandName, String modelName);

    Optional<List<ModelDto>> getModelsOfUniqueBrand(BrandDto brand);

    Optional<ModelDto> getModelWithUniqueName(String modelName);

    Optional<List<ModelDto>> getModelsOfCategory(Category category);

    void editModel(String modelName, String brandName, AddModelDto modelDto);

    void updateModelEndYear(int endYear, ModelDto modelDto);

    void addModelImage(String imageUrl, String id);

    void deleteModelById(String id);

    void deleteModelByBrandAndName(String brandName, String modelname);
}
