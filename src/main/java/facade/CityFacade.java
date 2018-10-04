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
import java.util.stream.Collectors;
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
		return cityMapper.getZipCodeList().stream().map(z -> new CityInfoDTO(z)).collect(Collectors.toList());
	}

	@Override
	public CityInfoDTO addCity(CityInfo city) {
		return new CityInfoDTO(cityMapper.addCity(city));
	}

	@Override
	public CityInfoDTO removeCity(String zipCode) {
		return new CityInfoDTO(cityMapper.removeCity(zipCode));
	}

	@Override
	public CityInfoDTO editCity(CityInfo city) {
		return new CityInfoDTO(cityMapper.editCity(city));
	}

	@Override
	public CityInfoDTO getCity(String zipCode) {
		return new CityInfoDTO(cityMapper.getCity(zipCode));
	}

}
