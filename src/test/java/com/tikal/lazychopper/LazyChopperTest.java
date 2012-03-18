package com.tikal.lazychopper;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import junit.framework.Assert;

import org.hibernate.proxy.HibernateProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tikal.lazychopper.sample.model.Car;
import com.tikal.lazychopper.sample.model.House;
import com.tikal.lazychopper.sample.model.Person;
import com.tikal.lazychopper.sample.service.CarService;
import com.tikal.lazychopper.sample.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml", "classpath:lazychopper-ApplicationContext.xml"})
public class LazyChopperTest {
	
	@Autowired
	private PersonService personService;
	
	@Autowired 
	private CarService carService;
	
	// test the saving a person
	@Test
	public void testChopping() throws JAXBException {
		Person p = personService.createPerson(new Person("aaa"));
		Car c = new Car("aaa");
		c.setPerson(p);
		carService.createCar(c);
		
		PersonContainer personContainer = new PersonContainer(personService.findAllWithInitProxy());
		
		JAXBContext context = JAXBContext.newInstance(PersonContainer.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(personContainer, System.out);
		
	}
	
	@Test
	public void testChopping2() throws JAXBException {
		Person p = personService.createPerson(new Person("bbb"));
		Car c = new Car("bbb");
		c.setPerson(p);
		carService.createCar(c);	
		
		List<Car> cars = carService.findAllWithLazyPerson(p.getId());
		
		CarContainer carContainer = new CarContainer(cars);
		
		JAXBContext context = JAXBContext.newInstance(CarContainer.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(carContainer, System.out);
		
		
	}
	
	@Test
	public void testChopping3()  {
		Person p = personService.createPerson(new Person("ccc"));		
		List<Person> persons = personService.findAllWithInitPersonProxy(p.getId());
		Assert.assertFalse(persons.get(0) instanceof HibernateProxy);
	}
	
	@Test
	public void testChopping4()  {
		Person p = personService.createPersonAndHouse(new Person("ddd"), new House("qqq"));		
		p = personService.findPersonWithInitilizedProxyHouse(p.getId(),p.getHouses().get(0).getId(),false);
		Assert.assertFalse((p.getHouses().get(0)) instanceof HibernateProxy);
		
		p = personService.findPersonWithInitilizedProxyHouse(p.getId(),p.getHouses().get(0).getId(),true);
		Assert.assertFalse((p.getHouses().get(0)) instanceof HibernateProxy);
		
	}
	
	
	
	@XmlRootElement(namespace = "urn:com.tikal.lazychopper.sample.model")
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "CarContainer", namespace = "urn:com.tikal.lazychopper.sample.model")
	public static class CarContainer{
		@SuppressWarnings("unused")
		private Collection<Car> cars;
		
		public CarContainer(){}

		public CarContainer(Collection<Car> cars) {
			this.cars = cars;
		}
		
	}
	
	@XmlRootElement(namespace = "urn:com.tikal.lazychopper.sample.model")
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "PersonContainer", namespace = "urn:com.tikal.lazychopper.sample.model")
	public static class PersonContainer{
		@SuppressWarnings("unused")
		private Collection<Person> persons;
		
		public PersonContainer(){}

		public PersonContainer(Collection<Person> persons) {
			this.persons = persons;
		}
		
	}
}
