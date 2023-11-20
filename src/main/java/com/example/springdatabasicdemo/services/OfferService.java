package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OfferService {

    Optional<OfferDto> getOfferById(String id);
    OfferDto createNewOffer(OfferDto offer);

    Optional<List<OfferDto>> getOffersLessThanPrice(BigDecimal price);

    Optional<List<OfferDto>> getOffersByEngineType(String engine);

    void updateOfferDescription(String description, String id);

    void deleteOfferById(String id);

    Boolean deleteOffersBySeller(UserDto seller);
}
