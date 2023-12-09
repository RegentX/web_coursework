package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    Offer findOfferById(String id);
    Offer findOfferByCreated(Date created);

    Offer findOfferBySeller(User seller);

    Offer findOfferByModelAndSeller(Model model, User user);

    List<Offer> findOffersByPriceLessThan(BigDecimal price);

    Offer findOfferByOfferYear(int offerYear);

    Offer findOfferByModel(Model model);

    List<Offer> findOffersBySeller(User seller);

    @Query("SELECT o FROM Offer o WHERE o.engine = :engine")
    List<Offer> findByEngine(@Param("engine") Engine engine);


    List<Offer> findOffersByModel(Model model);

    List<Offer> findOffersByOfferYear(int offerYear);

    List<Offer> findOffersByPrice(BigDecimal price);

    List<Offer> findOffersByCreated(Date created);

    void deleteOfferByModelAndSeller(Model model, User user);

    Offer deleteOfferById(String id);

}


