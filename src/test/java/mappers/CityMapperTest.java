/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.CityInfoDTO;
import entity.CityInfo;
import facade.CityFacade;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author martin
 */
public class CityMapperTest {

	public CityMapperTest() {
	}

	@Test
	public void testGetZipCodeList() {
		System.out.println("getZipCodeList");
		CityFacade cityFacade = new CityFacade(new CityMapper(Persistence.createEntityManagerFactory("jpaputest")));
		int length = 1;
		List<CityInfoDTO> result = cityFacade.getZipCodeList();
		assertEquals(length, result.size());
	}

	@Test
	public void testGetCityInfo() {
		System.out.println("getCityInfo");
		CityFacade cityFacade = new CityFacade(new CityMapper(Persistence.createEntityManagerFactory("jpaputest")));
		String expectedZip = "1000";
		String expectedName = "Testby";

		CityInfoDTO cityInfoDTO = cityFacade.getCity(expectedZip);
		assertEquals(expectedZip, cityInfoDTO.getZipCode());
		assertEquals(expectedName, cityInfoDTO.getCity());
	}

	@Test
	public void testAddCity() {
		System.out.println("addCity");
		CityFacade cityFacade = new CityFacade(new CityMapper(Persistence.createEntityManagerFactory("jpaputest")));
		CityInfo city = new CityInfo("9999", "TESTTEST");

		cityFacade.addCity(city);
		CityInfoDTO cityInfoDTO = new CityInfoDTO(city);

		assertEquals(cityInfoDTO.getCity(), cityFacade.getCity("9999").getCity());
	}

	@Test
	public void testRemoveCity() {
		System.out.println("RemoveCity");
		CityFacade cityFacade = new CityFacade(new CityMapper(Persistence.createEntityManagerFactory("jpaputest")));
		
		CityInfoDTO expected = cityFacade.getCity("9999");
		CityInfoDTO result = cityFacade.removeCity("9999");
		
		assertEquals(expected.getCity(), result.getCity());
		assertEquals(expected.getZipCode(), result.getZipCode());
	}

	@Test
	public void testEditCity() {
//		System.out.println("EditCity");
//		CityFacade cityFacade = new CityFacade(new CityMapper(Persistence.createEntityManagerFactory("jpaputest")));
//		CityInfo cityInfo = cityFacade.addCity(new CityInfo("testEdit", "editby"));
//		CityInfoDTO cityInfoDTO = cityFacade.getCity("testEdit");
//		cityInfoDTO.setCity("TESTBY");
//		cityFacade.editCity(cityInfoDTO);
//
//		assertEquals("TESTBY", cityMapper.getCity("testEdit").getCity());

	}

}
