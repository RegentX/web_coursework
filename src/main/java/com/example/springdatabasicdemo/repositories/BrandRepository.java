package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Brand findBrandById(String id);

    Brand findBrandByName(String name);

    Brand findBrandByCreated(Date created);

    Brand findBrandByModified(Date modified);

    List<Brand> findBrandsById(String id);

    List<Brand> findBrandsByName(String name);

    Brand deleteBrandById(String id);

    Brand deleteBrandByName(String name);
}


