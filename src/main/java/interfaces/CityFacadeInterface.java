/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTO.CityInfoDTO;
import entity.CityInfo;
import java.util.List;

/**
 *
 * @author wicktus
 */
public interface CityFacadeInterface {

	public List<CityInfoDTO> getZipCodeList();

	public CityInfoDTO addCity(CityInfo city);

	public CityInfoDTO removeCity(String zipCode);

	public CityInfoDTO editCity(CityInfo city);

	public CityInfoDTO getCity(String zipCode);
}
