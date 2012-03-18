package com.tikal.lazychopper.sample.service;

import java.util.List;

import com.tikal.lazychopper.sample.model.Car;

public interface CarService {
	Car createCar(Car car);
	List<Car> findAll();
	 List<Car> findAllWithLazyPerson(int personId);
}
