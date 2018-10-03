/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.CityInfoDTO;
import entity.CityInfo;
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
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpaputest"));
		int length = 12;
		for(int i = 0; i < 10; i++){
			cityMapper.addCity(new CityInfo("test" + i, "testby" + i));
		}
		List<CityInfoDTO> result = cityMapper.getZipCodeList();
		assertEquals(length, result.size());
	}

	@Test
	public void testGetCityInfo() {
		System.out.println("getCityInfo");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpaputest"));
		String expectedZip = "4400";
		String expectedName = "Kalundborg";
		cityMapper.addCity(new CityInfo(expectedZip, expectedName));

		CityInfoDTO cityInfoDTO = cityMapper.getCity(expectedZip);
		assertEquals(expectedZip, cityInfoDTO.getZipCode());
		assertEquals(expectedName, cityInfoDTO.getCity());
	}

	@Test
	public void testAddCity() {
		System.out.println("addCity");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpaputest"));
		CityInfo city = new CityInfo();
		city.setCity("test");
		city.setZipCode("99999");

		cityMapper.addCity(city);

		assertEquals("test", cityMapper.getCity("99999").getCity());
	}

	@Test
	public void testRemoveCity() {
		System.out.println("RemoveCity");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpaputest"));
		assertEquals("test", cityMapper.getCity("99999").getCity());
		cityMapper.removeCity("99999");

		assertEquals(null, cityMapper.getCity("99999"));
	}

	@Test
	public void testEditCity() {
		System.out.println("EditCity");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpaputest"));
		cityMapper.addCity(new CityInfo("testEdit", "editby"));
		CityInfoDTO cityInfoDTO = cityMapper.getCity("testEdit");
		cityInfoDTO.setCity("TESTBY");
		cityMapper.editCity(cityInfoDTO);

		assertEquals("TESTBY", cityMapper.getCity("testEdit").getCity());

	}

}
