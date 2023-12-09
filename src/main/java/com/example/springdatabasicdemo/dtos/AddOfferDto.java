package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import com.example.springdatabasicdemo.models.BaseEntity;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;


public class AddOfferDto {

    private String modelName;

    private String userName;

    private String description;

    private Engine engine;

    private String imageUrl;

    private int mileage;

    private BigDecimal price;

    private Transmission transmission;

    private int offerYear;

    @NotEmpty(message = "Model name cannot be null or empty!")
    @Length(min = 1, message = "Model name should be at least 1 characters long!")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @NotEmpty(message = "User name cannot be null or empty!")
    @Length(min = 1, message = "User name should be at least 1 characters long!")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotEmpty(message = "Description cannot be null or empty!")
    @Size(min = 1, max=100, message = "Description should be at least 1 characters long!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message="Category cannot be null. Please choose it!")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @NotEmpty(message = "Image url cannot be null or empty!")
    @Length(min = 10, max = 255, message = "URL of image must be more than 10 characters!")
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

    @DecimalMin(value = "360")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message="Transmission cannot be null. Please choose it!")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Min(value = 1880, message = "Year must be a number after 1880!")
    @NotNull(message = "Year must not be null or empty!")
    public int getOfferYear() {
        return offerYear;
    }

    public void setOfferYear(int offerYear) {
        this.offerYear = offerYear;
    }

}
