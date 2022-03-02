package com.atlas.mygoods.services;

import com.atlas.mygoods.models.AdditionalInfo.Car;
import com.atlas.mygoods.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(Long limit) {
        if (limit == 0) {
            return carRepository.findAll();
        }
        return carRepository.getAllCarWithLimit(limit);
    }

//    public List<Car> getAllCar() {
//        final List<Car> listOfCar = carRepository.findAll();
//        return new HashSet<>(listOfCar);
//    }
//
//    public List<Car> getLimitedCar(Long limit) {
//        final List<Car> listOfCar = carRepository.getAllCarWithLimit(limit);
//        return new HashSet<>(listOfCar);
//    }
}
