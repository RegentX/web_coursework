package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class OfferController {

    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("offers/{engine}")
    public ResponseEntity<Optional<List<OfferDto>>> getModelFromStartYear(@PathVariable String engine) {
        Optional<List<OfferDto>> offers = offerService.getOffersByEngineType(engine);
        return offers.map(offerDtos -> ResponseEntity.ok(Optional.of(offerDtos))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable String id) {
        Optional<OfferDto> offer = offerService.getOfferById(id);
        return offer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create-offer")
    public OfferDto createNewOffer(@RequestBody OfferDto offerDto) {
        return offerService.createNewOffer(offerDto);
    }

    @GetMapping("/priceLessThan/{price}")
    public Optional<List<OfferDto>> getOffersLessThanPrice(@PathVariable BigDecimal price) {
        return offerService.getOffersLessThanPrice(price);
    }

    @PutMapping("/update-offer/description/{offerId}")
    public void updateOfferDescription(@PathVariable String offerId, @RequestParam String description) {
        offerService.updateOfferDescription(description, offerId);
    }

    @DeleteMapping("/delete-offer/{offerId}")
    public void deleteOfferById(@PathVariable String offerId) {
        offerService.deleteOfferById(offerId);
    }

    @DeleteMapping("/delete-offer/seller")
    public Boolean deleteOffersBySeller(@RequestBody UserDto sellerDto) {
        return offerService.deleteOffersBySeller(sellerDto);
    }
}