package com.example.springdatabasicdemo.models;
import com.example.springdatabasicdemo.enums.Category;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    private String name;

    private Category category;

    private String imageUrl;

    private int startYear;

    private int endYear;

    private LocalDate created;

    private LocalDate modified;

    private Brand brand;

    private List<Offer> offers;

    protected Model() {
    }

    @Column(name = "model_name", nullable = false)
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category", nullable = false)
    public Category getCategory() {
        return category;
    }

    protected void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "start_year", nullable = false)
    public int getStartYear() {
        return startYear;
    }

    protected void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Column(name = "end_year", nullable = false)
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @Temporal(TemporalType.DATE) //change to DateTime
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    protected void setCreated(LocalDate created) {
        this.created = created;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "modified", nullable = false)
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = true)
    public Brand getBrand() {
        return brand;
    }

    protected void setBrand(Brand brand) {
        this.brand = brand;
    }

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
