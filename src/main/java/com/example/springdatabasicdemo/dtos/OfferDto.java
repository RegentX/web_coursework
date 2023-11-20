package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import com.example.springdatabasicdemo.models.BaseEntity;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


public class OfferDto {

    private String id;

    private LocalDate created;

    private String description;

    private Engine engine;

    private String imageUrl;

    private int mileage;

    private LocalDate modified;

    private BigDecimal price;

    private Transmission transmission;

    private int offerYear;


    protected OfferDto() {

    }

    public OfferDto(
            String id,
            String description,
            Engine engine,
            String imageUrl,
            int mileage,
            BigDecimal price,
            Transmission transmission,
            int offerYear,
            ModelDto model,
            UserDto seller,
            LocalDate created,
            LocalDate modified
            ) {
        this.created = created;
        this.modified = modified;
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.offerYear = offerYear;
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @NotNull
    @NotEmpty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @NotNull
    @NotEmpty
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @DecimalMin(value = "360")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getOfferYear() {
        return offerYear;
    }

    public void setOfferYear(int offerYear) {
        this.offerYear = offerYear;
    }


    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
