package com.springapi.carsGarage.repository;

import com.springapi.carsGarage.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
   List<Car> findByBrand(String brand);
   Car findByModel(String models);
}
