package com.tikal.lazychopper.sample.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.NaturalId;

@Entity
@XmlRootElement(namespace = "urn:com.tikal.lazychopper.sample.model")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Person", namespace = "urn:com.tikal.lazychopper.sample.model")
public class Person extends AbstractEntity {

	@NaturalId(mutable = true)
	private String name;

	@OneToMany
	@JoinColumn(name = "person_id")
	private List<House> houses = new LinkedList<House>();

	Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<House> getHouses() {
		return houses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addHouse(House house) {
		houses.add(house);
	}

}
