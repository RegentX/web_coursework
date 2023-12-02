package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.OfferRepository;
import com.example.springdatabasicdemo.services.OfferService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    ModelMapper modelMapper;

    OfferRepository offerRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    /*@Override
    public OfferDto createNewOffer(OfferDto offerEntity) {
        if (offerEntity.getId()!=null) {
            Optional<Offer> offer = offerRepository.findById(offerEntity.getId());
            if (offer.isPresent()) {
                OfferDto dto = modelMapper.map(offer,OfferDto.class);
                offerEntity.setCreated(dto.getCreated());
                offerEntity.setModified(LocalDate.now());
                Offer o = modelMapper.map(offerEntity, Offer.class);
                return modelMapper.map(offerRepository.save(o), OfferDto.class);
            }
        }
        offerEntity.setCreated(LocalDate.now());
        Offer o = modelMapper.map(offerEntity,Offer.class);
        return modelMapper.map(offerRepository.save(o), OfferDto.class);
    }*/

    @Override
    public Optional<OfferDto> getOfferById(String id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);

        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            OfferDto offerDto = modelMapper.map(offer, OfferDto.class);
            return Optional.of(offerDto);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public OfferDto createNewOffer(OfferDto offerEntity) {
            try {
                Offer offerEx = modelMapper.map(offerEntity, Offer.class);

                return modelMapper.map(offerRepository.saveAndFlush(offerEx), OfferDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }

        return offerEntity;
    }

    @Override
    public Optional<List<OfferDto>> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();

        if (!offers.isEmpty()) {
            List<OfferDto> offerDtos = offers.stream()
                    .map(offer -> modelMapper.map(offer, OfferDto.class))
                    .collect(Collectors.toList());

            return Optional.of(offerDtos);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<OfferDto>> getOffersLessThanPrice(BigDecimal price) {
        List<Offer> offers = offerRepository.findOffersByPriceLessThan(price);

        if (!offers.isEmpty()) {
            List<OfferDto> offerDtos = offers.stream()
                    .map(offer -> modelMapper.map(offer, OfferDto.class))
                    .collect(Collectors.toList());

            return Optional.of(offerDtos);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<OfferDto>> getOffersByEngineType(String engine) {
        Engine engineType = switch (engine) {
            case "electric" -> Engine.ELECTRIC;
            case "gasoline" -> Engine.GASOLINE;
            case "diesel" -> Engine.DIESEL;
            case "hybrid" -> Engine.HYBRID;
            default -> null;
        };
        List<Offer> offers = offerRepository.findByEngine(engineType);

        if (!offers.isEmpty()) {
            List<OfferDto> offerDtos = offers.stream()
                    .map(offer -> modelMapper.map(offer, OfferDto.class))
                    .collect(Collectors.toList());

            return Optional.of(offerDtos);
        }

        return Optional.empty();
    }

    @Override
    public void updateOfferDescription(String description, String id) {
        Optional<Offer> offerEx = offerRepository.findById(id);
        if (offerEx.isPresent()) {
            Offer offer = offerEx.get();
            offer.setDescription(description);
            offerRepository.save(offer);
        }
    }

    @Override
    public void deleteOfferById(String id) {
        offerRepository.deleteOfferById(id);
    }

    @Override
    public Boolean deleteOffersBySeller(UserDto seller) {
        User sellerEx = modelMapper.map(seller, User.class);
        Optional<List<Offer>> offers = Optional.ofNullable(offerRepository
                .findOffersBySeller(sellerEx));
        offers.ifPresent(offerList -> {
            // Delete each offer from the repository
            offerRepository.deleteAll(offerList);
        });

        return offers.isPresent();

    }

}
