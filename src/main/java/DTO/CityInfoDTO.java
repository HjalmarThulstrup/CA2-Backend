/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entity.CityInfo;

/**
 *
 * @author juanni420
 */
public class CityInfoDTO {
    private String city;
    private int zipCode;

    public CityInfoDTO(CityInfo cityInfo) {
        this.city = cityInfo.getCity();
        this.zipCode = cityInfo.getZipCode();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    
    
    
}
