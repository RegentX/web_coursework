package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.services.ModelService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl  implements ModelService {
    
    ModelMapper modelMapper;
    
    ModelRepository modelRepository;

    BrandRepository brandRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

//    @Override
//    public ModelDto createNewModel(ModelDto modelEntity) {
//        Model modelEx = modelMapper.map(modelEntity, Model.class);
//        if (modelEx != null) {
//            return modelMapper.map(modelRepository.save(modelEx), ModelDto.class);
//        } else {
//        throw new DataIntegrityViolationException("ModelDto is Empty");
//        }
//    }

    public List<ModelDto> getAllModels() {
        return modelRepository.findAll().stream().map(model -> {
            ModelDto modelDto = modelMapper.map(model, ModelDto.class);
            if (model.getBrand() != null) {
                modelDto.setBrand(model.getBrand().getName());
            }
            System.out.println(modelDto.getBrand());
            return modelDto;
        }).collect(Collectors.toList());
    }


    @Override
    public AddModelDto createNewModel(AddModelDto modelEntity) {
            try {
                String brandName = modelEntity.getBrand();
                Brand brand = brandRepository.findBrandByName(brandName);
                Model modelEx = modelMapper.map(modelEntity, Model.class);
                modelEx.setCreated(LocalDateTime.now());
                modelEx.setModified(LocalDateTime.now());
                modelEx.setBrand(brand);
                System.out.print(modelEx.getBrand());
                System.out.print("---------------");
                return modelMapper.map(modelRepository
                        .saveAndFlush(this.modelMapper.map(modelEx, Model.class)), AddModelDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!" + e);
            }
        return modelEntity;
    }

    @Override
    public Optional<List<ModelDto>> getModelsOfUniqueBrand(BrandDto brand) {
        Brand brandEx = modelMapper.map(brand, Brand.class);
        return Optional.of(Collections.singletonList(modelMapper.map(modelRepository.findModelsByBrand(brandEx), ModelDto.class)));
    }

    @Override
    public ModelAdditionalInfoDto getModelByBrandAndName(String brandName, String modelName) {
        Brand brand = brandRepository.findBrandByName(brandName);
        ModelAdditionalInfoDto modelDto =  modelMapper.map(modelRepository.findModelByBrandAndName(brand, modelName), ModelAdditionalInfoDto.class);
        modelDto.setBrand(brandName);
        return modelDto;
    }

    @Override
    public Optional<ModelDto> getModelWithUniqueName(String modelName) {
        return Optional.of(modelMapper.map(modelRepository.findModelByName(modelName), ModelDto.class));
    }

    @Override
    public Optional<List<ModelDto>> getModelsOfCategory(Category category) {
        return Optional.of(Collections.singletonList(modelMapper.map(modelRepository.findModelsByCategory(category), ModelDto.class)));
    }

    @Override
    public void editModel(String modelName, String brandName, AddModelDto modelDto) {
        Brand brandEx = brandRepository.findBrandByName(brandName);
        Model modelEx = modelRepository.findModelByBrandAndName(brandEx, modelName);

        Brand brandEx2 = brandRepository.findBrandByName(modelDto.getBrand());

        modelEx.setBrand(brandEx2);
        modelEx.setCategory(modelDto.getCategory());
        modelEx.setName(modelDto.getName());
        modelEx.setImageUrl(modelDto.getImageUrl());
        modelEx.setEndYear(modelDto.getEndYear());
        modelEx.setStartYear(modelDto.getStartYear());
        modelEx.setModified(LocalDateTime.now());

        modelRepository.saveAndFlush(modelEx);

    }

    @Override
    public void updateModelEndYear(int endYear, ModelDto modelDto) {
        if (modelDto.getName() !=  null) {
            Optional<Model> modelEx = Optional.ofNullable(modelRepository.findModelByName(modelDto.getName()));
            if (modelEx.isPresent()) {
                Model model = modelEx.get();
                model.setEndYear(endYear);
                modelRepository.save(model);
            }
        }
    }

    @Override
    public void addModelImage(String imageUrl, String id) {
        if (id !=  null) {
            Optional<Model> modelEx = modelRepository.findById(id);
            if (modelEx.isPresent()) {
                Model model = modelEx.get();
                model.setImageUrl(imageUrl);
                modelRepository.save(model);
            }
        }
    }

    @Override
    public void deleteModelById(String id) {
        modelRepository.deleteModelById(id);
    }

    @Transactional
    @Override
    public void deleteModelByBrandAndName(String brandName, String modelName) {
        try {
            Brand brand = brandRepository.findBrandByName(brandName);
            modelRepository.deleteModelByBrandAndAndName(brand, modelName);
        } catch (Exception e) {
            System.out.println("Some thing went wrong!" + e);
        }

    }

}
