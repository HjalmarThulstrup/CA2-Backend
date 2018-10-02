/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entity.Hobby;
import entity.Person;
import java.util.ArrayList;
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
	private List<String> phoneNums;
	private List<String> hobbies;
	private String streetAndCity;

	public PersonDTO(Person p) {
		this.id = p.getId();
		this.email = p.getEmail();
		this.firstName = p.getFirstName();
		this.lastName = p.getLastName();
		this.phoneNums = p.getPhoneNumsString();
		hobbies = new ArrayList<>();
		for (Hobby h : p.getHobbyList()) {
			hobbies.add(h.toString());
		}
		this.streetAndCity = p.getAddress().getStreet() + " " + p.getAddress().getCityInfo();
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

	public List<String> getPhoneNums() {
		return phoneNums;
	}

	public void setPhoneNums(List<String> phoneNums) {
		this.phoneNums = phoneNums;
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
