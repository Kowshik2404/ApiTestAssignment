package com.assignment.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	private String name;

	private String nationality;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
