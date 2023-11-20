package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.BaseEntity;
import com.example.springdatabasicdemo.models.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrandDto {

        private String id;

        public String name;

        public LocalDate created;

        public LocalDate modified;

        protected BrandDto() {
        }

        public BrandDto(String id, String name, LocalDate created, LocalDate modified) {
                this.id = id;
            this.name = name;
            this.created = created;
            this.modified = modified;
        }

        @NotNull
        @NotEmpty
        public String getName() {return name;}

        protected void setName(String name) { this.name = name;}

        public LocalDate getCreated() {return created;}

        public void setCreated(LocalDate created) {this.created = created;}

        public LocalDate getModified() {return modified;}

        public void setModified(LocalDate modified) {this.modified = modified;}

        public String getId() {
                return id;
        }

        protected void setId(String id) {
                this.id = id;
        }
}
