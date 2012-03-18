package com.tikal.lazychopper.sample.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement(namespace = "urn:com.tikal.lazychopper.sample.model")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Car", namespace = "urn:com.tikal.lazychopper.sample.model")
public class Car extends AbstractEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	private String name;

	Car() {
	}

	public Car(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
