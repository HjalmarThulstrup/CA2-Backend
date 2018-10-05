/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entity.Address;
import entity.CityInfo;

/**
 *
 * @author juanni420
 */
public class AddressDTO{
    private int id;
    private String city;
    private String zip;
    private String street;
    private String addInfo;
    

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.addInfo = address.getAdditionalInfo();
        this.zip = address.getCityInfo().getZipCode();
        this.city = address.getCityInfo().getCity();;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
    
    
    
}
