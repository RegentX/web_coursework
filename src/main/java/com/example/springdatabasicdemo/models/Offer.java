package com.example.springdatabasicdemo.models;


import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{

    private LocalDate created;

    private String description;

    private Engine engine;

    private String imageUrl;

    private int mileage;

    private LocalDate modified;

    private BigDecimal price;

    private Transmission transmission;

    private int offerYear;

    private Model model;

    private User seller;

    protected Offer() {

    }

    public Offer(
            LocalDate created,
            LocalDate modified,
            String description,
            Engine engine,
            String imageUrl,
            int mileage,
            BigDecimal price,
            Transmission transmission,
            int offerYear,
            Model model,
            User seller) {
        this.created = created;
        this.modified = modified;
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.offerYear = offerYear;
        this.model = model;
        this.seller = seller;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "engine", nullable = false)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "mileage", nullable = false)
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "modified", nullable = false)
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transmission", nullable = false)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "offer_year", nullable = false)
    public int getOfferYear() {
        return offerYear;
    }

    public void setOfferYear(int offerYear) {
        this.offerYear = offerYear;
    }

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}

