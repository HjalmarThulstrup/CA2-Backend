/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entity.Person;
import java.util.List;

/**
 *
 * @author martin
 */
public class PersonDTO {
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private int phone;
	private String phoneDesc;
	private List<String> hobbies;
	private String streetAndCity;

	public PersonDTO(Person p) {
		this.id = p.getId;
		this.email = p.getEmail;
		this.firstName = p.getFirstName;
		this.lastName = p.getLastName;
		this.phone = p.getPhone;
		this.phoneDesc = p.getPhoneDesc;
		this.hobbies = p.getHobbies;
		this.streetAndCity = p.getStreetAndCity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getPhoneDesc() {
		return phoneDesc;
	}

	public void setPhoneDesc(String phoneDesc) {
		this.phoneDesc = phoneDesc;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public String getStreetAndCity() {
		return streetAndCity;
	}

	public void setStreetAndCity(String streetAndCity) {
		this.streetAndCity = streetAndCity;
	}
	
	
	
	
}
