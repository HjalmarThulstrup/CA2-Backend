/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.CityInfoDTO;
import entity.CityInfo;
import interfaces.CityFacadeInterface;
import java.util.List;
import mappers.CityMapper;

/**
 *
 * @author martin
 */
public class CityFacade implements CityFacadeInterface {

	private CityMapper cityMapper;

	public CityFacade(CityMapper cityMapper) {
		this.cityMapper = cityMapper;
	}

	@Override
	public List<CityInfoDTO> getZipCodeList() {
		return cityMapper.getZipCodeList();
	}

	@Override
	public CityInfoDTO addCity(CityInfo city) {
		return cityMapper.addCity(city);
	}

	@Override
	public boolean removeCity(String id) {
		return cityMapper.removeCity(id);
	}

	@Override
	public CityInfoDTO editCity(CityInfoDTO city) {
		return cityMapper.editCity(city);
	}

	@Override
	public CityInfoDTO getCity(String zipCode) {
		return cityMapper.getCity(zipCode);
	}

}
