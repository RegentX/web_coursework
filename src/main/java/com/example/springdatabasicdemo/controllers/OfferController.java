package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.*;
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

    @Autowired
    private final UserService userService;

    @Autowired
    private final ModelService modelService;

    public OfferController(OfferService offerService, UserService userService, ModelService modelService) {
        this.offerService = offerService;
        this.userService = userService;
        this.modelService = modelService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("modelList", modelService.getAllModels());
        return "offer-add";
    }

    @ModelAttribute("offerModel")
    public OfferDto initOffer() {return new OfferDto();}

    @PostMapping("/create")
    public String createOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.createNewOffer(offerModel);

        return "redirect:/offers/all";
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

    @GetMapping("/priceLessThan/{price}")
    public Optional<List<OfferDto>> getOffersLessThanPrice(@PathVariable BigDecimal price) {
        return offerService.getOffersLessThanPrice(price);
    }

    @PutMapping("/update-offer/description/{offerId}")
    public void updateOfferDescription(@PathVariable String offerId, @RequestParam String description) {
        offerService.updateOfferDescription(description, offerId);
    }

    @GetMapping("/offer-details/{modelName}/{userName}")
    public String OfferDetails(@PathVariable String modelName, @PathVariable String userName, Model model) {
        model.addAttribute("offerDetails",offerService.getOfferBySellerAndModel(modelName, userName));
        return "offer-detail";
    }

    @PostMapping("/update/price/{price}")
    public String updateOfferPrice(@PathVariable BigDecimal price) {

        return "redirect:/offers/all";
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

    @PostMapping("/delete/offer/{modelName}/{userName}")
    public String deleteOfferByUserAndModel(@PathVariable String modelName, @PathVariable String userName) {
        offerService.deleteOfferByModelAndSeller(modelName, userName);
        return "redirect:/offers/all";
    }
}