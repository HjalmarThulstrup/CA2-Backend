/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.CityInfoDTO;
import interfaces.CityFacadeInterface;
import java.util.List;

/**
 *
 * @author martin
 */
public class CityFacade implements CityFacadeInterface{

	@Override
	public List<CityInfoDTO> getZipCodeList() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public CityInfoDTO addCity(CityInfoDTO city) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public CityInfoDTO removeCity(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public CityInfoDTO editCity(CityInfoDTO city) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public CityInfoDTO getCity(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
