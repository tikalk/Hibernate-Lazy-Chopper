package com.tikal.lazychopper.sample.service;

import java.util.List;

import com.tikal.lazychopper.sample.model.House;
import com.tikal.lazychopper.sample.model.Person;

public interface PersonService {
	Person createPerson(Person person);
	List<Person> findAll();
	List<Person> findAllWithInitProxy();
	List<Person> findAllWithInitPersonProxy(int personId);
	Person createPersonAndHouse(Person person, House house);
	Person findPersonWithInitilizedProxyHouse (int personId,int houseId,boolean fetch);
}
