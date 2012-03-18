package com.tikal.lazychopper.sample.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tikal.lazychopper.sample.model.Car;
import com.tikal.lazychopper.sample.model.Person;
import com.tikal.lazychopper.sample.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Car createCar(Car car) {
		em.persist(car);
		return car;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findAll() {
		return em.createQuery("select c from Car c").getResultList();
	}

	@Override
	public List<Car> findAllWithLazyPerson(int personId) {
		Person person = em.getReference(Person.class, personId);
		List<Car> cars = em.createQuery("select c from Car c join fetch c.person").getResultList();
		return cars;
	}
	
	

}
