package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.services.ModelService;
import com.example.springdatabasicdemo.services.OfferService;
import com.example.springdatabasicdemo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private final OfferService offerService;
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/add")
    public String addOffer() {return "offer-add";}

    @ModelAttribute("offerModel")
    public OfferDto initOffer() {return new OfferDto();}

    @PostMapping("/create")
    public String createOffer(@Valid OfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.createNewOffer(offerModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllOffers(Model model) {
        model.addAttribute("offerList", offerService.getAllOffers());

        return "offer-all";
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable String id) {
        Optional<OfferDto> offer = offerService.getOfferById(id);
        return offer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public String createNewOffer(@Valid OfferDto offerDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("offerModel", offerDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    result);
            return "redirect:/offers/all";
        }
        offerService.createNewOffer(offerDto);
        return "redirect:/offers/all";
    }

    @GetMapping("/priceLessThan/{price}")
    public Optional<List<OfferDto>> getOffersLessThanPrice(@PathVariable BigDecimal price) {
        return offerService.getOffersLessThanPrice(price);
    }

    @PutMapping("/update-offer/description/{offerId}")
    public void updateOfferDescription(@PathVariable String offerId, @RequestParam String description) {
        offerService.updateOfferDescription(description, offerId);
    }

    @GetMapping("delete/id/{id}")
    public String deleteOfferById(@PathVariable("id") String offerId) {
        offerService.deleteOfferById(offerId);
        return "redirect:/offers/all";
    }

    @DeleteMapping("/delete-offer/seller")
    public Boolean deleteOffersBySeller(@RequestBody UserDto sellerDto) {
        return offerService.deleteOffersBySeller(sellerDto);
    }
}