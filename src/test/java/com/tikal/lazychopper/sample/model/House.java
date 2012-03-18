package com.tikal.lazychopper.sample.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement(namespace = "urn:com.tikal.lazychopper.sample.model")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "House", namespace = "urn:com.tikal.lazychopper.sample.model")
public class House extends AbstractEntity {

	private String address;

	House() {
	}

	public House(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
