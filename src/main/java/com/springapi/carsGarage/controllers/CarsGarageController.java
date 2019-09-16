package com.springapi.carsGarage.controllers;

import com.springapi.carsGarage.entity.Car;
import com.springapi.carsGarage.exceptions.ResourceNotFound;
import com.springapi.carsGarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//CAN BE SET AT CONTROLLER LEVEL
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 5)
public class CarsGarageController {

    @Autowired
    private CarRepository carRepo;

    //CAN BE SET AT METHOD LEVEL AS WELL
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 5)
    @GetMapping(name = "/cars")
    public List<Car> getCars(){
        return carRepo.findAll();
    }

    @GetMapping(path = "/car/{carId}")
    public Car getCarById(@PathVariable(name = "carId") String tzz){
        return carRepo.findById(tzz).orElseThrow(() -> new ResourceNotFound()) ;
    }

    @PostMapping(path = "/car")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Car postCar(@RequestBody Car car){
        return carRepo.save(car);
    }

    @PutMapping(path = "/car/{id}")
    public Car updateCar(@PathVariable String id, @RequestBody Car updatedCar ){
        Car foundCar = carRepo.findById(id).orElseThrow(() -> new ResourceNotFound());
        foundCar.setBrand(updatedCar.getBrand());
        foundCar.setModel(updatedCar.getModel());
        foundCar.setColor(updatedCar.getColor());
        foundCar.setHorsePower(updatedCar.getHorsePower());

        return carRepo.save(foundCar);
    }

    @DeleteMapping(path = "/car/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteCar(@PathVariable(name = "id") String carId){
        Car foundCar = carRepo.findById(carId).orElseThrow(() -> new ResourceNotFound());
        carRepo.delete(foundCar);
    }
}
