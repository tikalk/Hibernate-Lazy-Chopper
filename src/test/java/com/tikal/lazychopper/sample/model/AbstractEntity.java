package com.tikal.lazychopper.sample.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * This class is our base entity and uses generated id. For this reason we do
 * NOT overide equals/hashcode
 * 
 */

@MappedSuperclass
@XmlRootElement(namespace = "urn:com.tikal.lazychopper.sample.model")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractEntity", namespace = "urn:com.tikal.lazychopper.sample.model")
public abstract class AbstractEntity {
	// @GenericGenerator(name = "Hilo", strategy = "hilo")
	// @GeneratedValue(generator = "Hilo")
	@Id
	@GeneratedValue
	@Access(AccessType.PROPERTY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	@SuppressWarnings("unused")
	protected void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public void setIdForTest(Integer id) { this.id = id; }
	 */

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "id=" + this.getId();
	}

}