package com.example.springdatabasicdemo.init;


import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import com.example.springdatabasicdemo.enums.UserRole;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

        @Autowired
        BrandService brandService;
        @Autowired
        ModelService modelService;
        @Autowired
        UserService userService;
        @Autowired
        RoleService roleService;
        @Autowired
        OfferService offerService;

        @Override
        public void run(String... args) throws Exception {
            initializeData();
        }

        public void initializeData() {


//                BrandDto brandDto1 = new BrandDto(null, "BMW", LocalDate.now(), LocalDate.now());
//                BrandDto brandDto2 = new BrandDto(null, "Lexus", LocalDate.now(), LocalDate.now());
//                BrandDto brandDto3 = new BrandDto(null, "Lamborghini", LocalDate.now(), LocalDate.now());
//                BrandDto brandDto4 = new BrandDto(null, "Audi", LocalDate.now(), LocalDate.now());
//                BrandDto brandDto5 = new BrandDto(null, "Chery", LocalDate.now(), LocalDate.now());
//                brandDto1 = brandService.addBrand(brandDto1);
//                brandDto2 = brandService.addBrand(brandDto2);
//                brandDto3 = brandService.addBrand(brandDto3);
//                brandDto4 = brandService.addBrand(brandDto4);
//                brandDto5 = brandService.addBrand(brandDto5);
//
//                ModelDto modelDto1 = new ModelDto(null, "Urus", Category.Car, "urus.jpg", 2001, 2023, brandDto1, LocalDate.now(), LocalDate.now());
//                ModelDto modelDto2 = new ModelDto(null, "Askona", Category.Truck, "askona.jpg", 2002, 2022, brandDto2, LocalDate.now(), LocalDate.now());
//                ModelDto modelDto3 = new ModelDto(null, "X5", Category.Buss, "x5.jpg", 2003, 2021, brandDto3, LocalDate.now(), LocalDate.now());
//                ModelDto modelDto4 = new ModelDto(null, "Tiggo Pro 4", Category.Motorcycle, "tiggo_pro_4.jpg", 2004, 2023, brandDto4, LocalDate.now(), LocalDate.now());
//                ModelDto modelDto5 = new ModelDto(null, "RX7", Category.Car, "rx_7.jpg", 2005, 2017, brandDto5, LocalDate.now(), LocalDate.now());
//
//            modelDto1 =  modelService.createNewModel(modelDto1);
//            modelDto2 = modelService.createNewModel(modelDto2);
//            modelDto3 = modelService.createNewModel(modelDto3);
//            modelDto4 = modelService.createNewModel(modelDto4);
//            modelDto5 = modelService.createNewModel(modelDto5);

//                RoleDto role1 = new RoleDto(null, UserRole.USER);
//                RoleDto role2 = new RoleDto(null, UserRole.ADMIN);
//            role1 = roleService.createRole(role1);
//            role2 = roleService.createRole(role2);
//
//                UserDto userDto1 = new UserDto(null, true, "John", "john.jpg", "Doe", "password", "john.doe", role1, LocalDate.now(), LocalDate.now());
//                UserDto userDto2 = new UserDto(null, false, "Mike", "mike.jpg", "Doe", "password2", "john.doe", role1, LocalDate.now(), LocalDate.now());
//                UserDto userDto3 = new UserDto(null, true, "Gary", "gary.jpg", "Doe", "password3", "john.doe", role1, LocalDate.now(), LocalDate.now());
//                UserDto userDto4 = new UserDto(null, false, "Nico", "nico.jpg", "Doe", "password4", "john.doe", role1, LocalDate.now(), LocalDate.now());
//                UserDto userDto5 = new UserDto(null, true, "Salamon", "salamon.jpg", "Doe", "password5", "john.doe", role2, LocalDate.now(), LocalDate.now());
//            userDto1 = userService.registerUser(userDto1);
//            userDto2 = userService.registerUser(userDto2);
//            userDto3 = userService.registerUser(userDto3);
//            userDto4 = userService.registerUser(userDto4);
//            userDto5 = userService.registerUser(userDto5);
//
//
//                OfferDto offerDto1 = new OfferDto(null, "Offer1", Engine.DIESEL, "offer1.jpg", 50000, BigDecimal.valueOf(10000), Transmission.MANUAL, 2022, modelDto1, userDto1, LocalDate.of(2023, 10, 11), LocalDate.now());
//                OfferDto offerDto2 = new OfferDto(null, "Offer2", Engine.DIESEL
//                        , "offer2.jpg", 60000, BigDecimal.valueOf(10000), Transmission.MANUAL, 2022, modelDto2, userDto2, LocalDate.of(2023, 10, 10), LocalDate.now());
//                OfferDto offerDto3 = new OfferDto(null, "Offer3", Engine.ELECTRIC, "offer3.jpg", 70000, BigDecimal.valueOf(10000), Transmission.MANUAL, 2022, modelDto3, userDto3, LocalDate.of(2023, 10, 10), LocalDate.now());
//                OfferDto offerDto4 = new OfferDto(null, "Offer4", Engine.HYBRID, "offer4.jpg", 80000, BigDecimal.valueOf(10000), Transmission.MANUAL, 2022, modelDto4, userDto4, LocalDate.of(2023, 10, 12), LocalDate.now());
//                OfferDto offerDto5 = new OfferDto(null, "Offer5", Engine.GASOLINE, "offer5.jpg", 90000, BigDecimal.valueOf(10000), Transmission.MANUAL, 2022, modelDto5, userDto5, LocalDate.of(2023, 10, 14), LocalDate.now());
//                offerService.createNewOffer(offerDto1);
//                offerService.createNewOffer(offerDto2);
//                offerService.createNewOffer(offerDto3);
//                offerService.createNewOffer(offerDto4);
//                offerService.createNewOffer(offerDto5);
            }

    }
