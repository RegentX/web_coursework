package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Offer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelDto {

    private String id;

    private String name;

    private Category category;

    private String imageUrl;

    private int startYear;

    private int endYear;

    private LocalDate created;

    private LocalDate modified;

    private BrandDto brand;


    // Пустой конструктор для Hibernate, обратите внимание на модификатор доступа
    protected ModelDto() {
    }

    public ModelDto(String id, String name, Category someCategory, String imageUrl, int startYear, int endYear, BrandDto brand, LocalDate created, LocalDate modified) {
        this.id = id;
        this.name = name;
        this.category = someCategory;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
        this.created = created;
        this.modified = modified;
    }

    @NotNull
    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull
    @NotEmpty
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
