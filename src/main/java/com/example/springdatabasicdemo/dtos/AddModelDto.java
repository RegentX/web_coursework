package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Offer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddModelDto {

    private String brand;

    private String name;

    private Category category;

    private String imageUrl;

    private int startYear;

    private int endYear;

    @NotEmpty(message = "Name of model name cannot be null or empty!")
    @Length(min = 2, message = "Name of model name should be at least 2 characters long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Category cannot be null. Please choose it!")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotEmpty(message = "Image url cannot be null or empty!")
    @Length(min = 10, max = 255, message = "URL of image must be more than 10 characters!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Min(value = 1880, message = "Start year must be a year after 1880!")
    @NotNull(message = "Start year must not be null or empty!")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Min(value = 1880, message = "Start year must be a year after 1880!")
    @NotNull(message = "End year must not be null!")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @NotEmpty(message = "Brand name cannot be null or empty!")
    @Length(min = 2, message = "Brand name should be at least 2 characters long!")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
