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
    private String streetAndInfo;
    private String cityInfo;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.streetAndInfo = address.getStreet() + ", " + address.getAdditionalInfo();
        this.cityInfo = address.getCityInfo().getZipCode() + " " + address.getCityInfo().getCity();
    }

    public int getId() {
        return id;
    }

    public String getStreetAndInfo() {
        return streetAndInfo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setStreetAndInfo(String streetAndInfo) {
        this.streetAndInfo = streetAndInfo;
    }

    public String getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
    }

    
    
    


    
    
    
    
    
}
