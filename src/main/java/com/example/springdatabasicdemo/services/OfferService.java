package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddOfferDto;
import com.example.springdatabasicdemo.dtos.OfferAdditionalInfoDto;
import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OfferService {

    Optional<OfferDto> getOfferById(String id);
    AddOfferDto createNewOffer(AddOfferDto offer);

    List<OfferDto> getAllOffers();

    List<OfferAdditionalInfoDto> getOffersBySeller(String userName);

    OfferAdditionalInfoDto getOfferBySellerAndModel(String modelName, String userName);

    Optional<List<OfferDto>> getOffersLessThanPrice(BigDecimal price);

    Optional<List<OfferDto>> getOffersByEngineType(String engine);

    void updateOfferDescription(String description, String id);

    void deleteOfferById(String id);

    void deleteOfferByModelAndSeller(String modelName, String userName);

    Boolean deleteOffersBySeller(UserDto seller);
}
