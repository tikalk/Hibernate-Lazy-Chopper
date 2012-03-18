package com.tikal.lazychopper.sample.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tikal.lazychopper.sample.model.House;
import com.tikal.lazychopper.sample.model.Person;
import com.tikal.lazychopper.sample.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Person createPerson(Person person) {
		em.persist(person);
		return person;
	}
	
	@Override
	public Person createPersonAndHouse(Person person, House house) {
		em.persist(person);
		em.persist(house);
		person.addHouse(house);
		return person;
	}
	
	@Override
	public Person findPersonWithInitilizedProxyHouse(int personId,int houseId,boolean fetch) {
		em.getReference(House.class,houseId);
		Query q;
		if(fetch)
			q = em.createQuery("select p from Person p join fetch p.houses where p.id = :pId");
		else{
			q = em.createQuery("select p from Person p where p.id = :pId");
		}
		Person p = (Person) q.setParameter("pId", personId).getSingleResult();
		Hibernate.initialize(p.getHouses());//init manually in case fetch=false
		return p;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAll() {
		return em.createQuery("select p from Person p").getResultList();
	}


	@Override
	public List<Person> findAllWithInitProxy() {
		 em.createQuery("select c from Car c").getResultList();
		List<Person> persons = findAll();
		return persons;
	}
	
	@Override
	public List<Person> findAllWithInitPersonProxy(int personId) {
		Person person = em.getReference(Person.class, personId);
		person.getName();//initialize it
		List<Person> persons = findAll();
		return persons;
	}

}
