/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.CityInfo;
import java.util.List;

/**
 *
 * @author wicktus
 */
public interface CityFacadeInterface {

    public List<CityDTO> getZipCodeList();
    
    public CityDTO addCity(CityInfo city);
    public CityDTO removeCity(int id);
    public CityDTO editCity(CityInfo city);
	public CityDTO getCity(int id);
}
