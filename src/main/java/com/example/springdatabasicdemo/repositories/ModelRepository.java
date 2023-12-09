package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    Model findModelByBrandAndName(Brand brand, String name);

    Model findModelById(String id);

    Model findModelByName(String name);

    Model findModelByStartYear(int startYear);

    List<Model> findModelsByCategory(Category category);

    List<Model> findModelsByBrand(Brand brand);

    List<Model> findModelsByStartYear(int startYear);

    Model deleteModelById(String id);

    void deleteModelByBrandAndAndName(Brand brand, String name);

}


