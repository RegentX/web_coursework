package com.example.springdatabasicdemo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddBrandDto {

    public String name;

    @NotEmpty(message = "Name of brand cannot be null or empty!")
    @Size(min = 2, message = "Name of brand must be more than 2 characters!")
    public String getName() {return name;}

    public void setName(String name) { this.name = name;}

}
