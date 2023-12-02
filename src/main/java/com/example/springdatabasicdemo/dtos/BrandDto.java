package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.BaseEntity;
import com.example.springdatabasicdemo.models.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrandDto {

        public String name;

        public LocalDate created;

        public LocalDate modified;

        public BrandDto() {
        }

        @NotEmpty(message = "Name of brand cannot be null or empty!")
        @Size(min = 2, message = "Name of brand must be more than 2 characters!")
        public String getName() {return name;}

        public void setName(String name) { this.name = name;}

        @NotNull(message = "Date of creation cannot be null or empty!")
        public LocalDate getCreated() {return created;}

        public void setCreated(LocalDate created) {this.created = created;}

        @NotNull(message = "Date of modification cannot be null or empty!")
        public LocalDate getModified() {return modified;}

        public void setModified(LocalDate modified) {this.modified = modified;}

}
