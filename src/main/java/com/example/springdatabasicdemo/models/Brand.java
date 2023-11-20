package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{

    public String name;

    public LocalDate created;

    public LocalDate modified;

    private List<Model> models;

    protected Brand() {
    }

    @Column(name = "brand_name", nullable = false)
    public String getName() {return name;}

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Model> getModels() {return models;}

    public void setModels(List<Model> models) {this.models = models;}

    protected void setName(String name) { this.name = name;}

    @Temporal(TemporalType.DATE)
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {return created;}

    protected void setCreated(LocalDate created) {this.created = created;}

    @Temporal(TemporalType.DATE)
    @Column(name = "modified", nullable = false)
    public LocalDate getModified() {return modified;}

    public void setModified(LocalDate modified) {this.modified = modified;}
}
