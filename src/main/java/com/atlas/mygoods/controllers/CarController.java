package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.AdditionalInfo.Car;
import com.atlas.mygoods.models.AdditionalInfo.CarResponse;
import com.atlas.mygoods.repositories.CarRepository;
import com.atlas.mygoods.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping
    public CarResponse getCars(@RequestParam(name = "limit") Long limit) {
        final List<Car> cars = new ArrayList<>(carService.getCars(limit));
        return new CarResponse(cars.size(), cars);
    }



}
