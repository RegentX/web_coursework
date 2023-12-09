package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.AddOfferDto;
import com.example.springdatabasicdemo.dtos.OfferAdditionalInfoDto;
import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.repositories.OfferRepository;
import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    ModelMapper modelMapper;

    OfferRepository offerRepository;

    ModelRepository modelRepository;

    UserRepository userRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

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


    public AddOfferDto createNewOffer(AddOfferDto offerEntity) {
            try {
                Model modelEx = modelRepository.findModelByName(offerEntity.getModelName());
                User userEx = userRepository.findUserByUsername(offerEntity.getUserName());
                Offer offerEx = modelMapper.map(offerEntity, Offer.class);
                offerEx.setModel(modelEx);
                offerEx.setSeller(userEx);
                offerEx.setCreated(LocalDateTime.now());
                offerEx.setModified(LocalDateTime.now());
                return modelMapper.map(offerRepository.saveAndFlush(offerEx), AddOfferDto.class);

            } catch (Exception e) {
                System.out.println("Some thing went wrong!"  + e);
            }

        return offerEntity;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream().map(model -> {
            OfferDto offerDto = modelMapper.map(model, OfferDto.class);
            if (model.getModel() != null) {
                offerDto.setModelName(model.getModel().getName());
            }
            if (model.getSeller() != null) {
                offerDto.setUserName(model.getSeller().getUsername());
            }
            System.out.println(offerDto.getModelName());
            System.out.println(offerDto.getUserName());
            return offerDto;
        }).collect(Collectors.toList());
    }

    public OfferAdditionalInfoDto getOfferBySellerAndModel(String modelName, String userName) {
        Model model = modelRepository.findModelByName(modelName);
        System.out.println(model.getId());
        User user = userRepository.findUserByUsername(userName);
        Offer offer =  offerRepository.findOfferByModelAndSeller(model, user);
        OfferAdditionalInfoDto offerDto = modelMapper.map(offer, OfferAdditionalInfoDto.class);
        if (offer.getSeller() != null) {
            offerDto.setUserName(userName);
        }
        if (offer.getModel() != null) {
            offerDto.setModelName(modelName);
        }
        System.out.println(offerDto.getModelName());
        System.out.println(offerDto.getUserName());
        System.out.println("-------");
        return offerDto;
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
    public List<OfferAdditionalInfoDto> getOffersBySeller(String userName) {
        User userEx = userRepository.findUserByUsername(userName);

            return offerRepository.findOffersBySeller(userEx).stream().map(m -> {
                OfferAdditionalInfoDto offerAdditionalInfoDto = modelMapper.map(m, OfferAdditionalInfoDto.class);

                if (m.getModel() != null) {
                    offerAdditionalInfoDto.setModelName(m.getModel().getName());
                }
                if (m.getSeller() != null) {
                    offerAdditionalInfoDto.setUserName(m.getSeller().getUsername());
                }
                return offerAdditionalInfoDto;
            }).collect(Collectors.toList());

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

    @Transactional
    @Override
    public void deleteOfferByModelAndSeller(String modelName, String userName) {
        Model model = modelRepository.findModelByName(modelName);
        User user = userRepository.findUserByUsername(userName);
        offerRepository.deleteOfferByModelAndSeller(model, user);
    }

}
