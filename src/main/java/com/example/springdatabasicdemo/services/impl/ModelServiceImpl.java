package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.services.ModelService;
import com.example.springdatabasicdemo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModelServiceImpl  implements ModelService {
    
    ModelMapper modelMapper;
    
    ModelRepository modelRepository;

    ValidationUtil validationUtil;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {this.validationUtil = validationUtil; }


//    @Override
//    public ModelDto createNewModel(ModelDto modelEntity) {
//        Model modelEx = modelMapper.map(modelEntity, Model.class);
//        if (modelEx != null) {
//            return modelMapper.map(modelRepository.save(modelEx), ModelDto.class);
//        } else {
//        throw new DataIntegrityViolationException("ModelDto is Empty");
//        }
//    }
    
    @Override
    public ModelDto createNewModel(ModelDto modelEntity) {
        if (!this.validationUtil.isValid(modelEntity)) {
            this.validationUtil
                    .violations(modelEntity)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                Model modelEx = modelMapper.map(modelEntity, Model.class);

                return modelMapper.map(modelRepository
                        .saveAndFlush(this.modelMapper.map(modelEntity, Model.class)), ModelDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return modelEntity;
    }

    @Override
    public Optional<List<ModelDto>> getModelsOfUniqueBrand(BrandDto brand) {
        Brand brandEx = modelMapper.map(brand, Brand.class);
        return Optional.of(Collections.singletonList(modelMapper.map(modelRepository.findModelsByBrand(brandEx), ModelDto.class)));
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

}