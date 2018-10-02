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

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getZipCodeList method, of class CityMapper.
	 */
	@Test
	public void testGetZipCodeList() {
		System.out.println("getZipCodeList");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpapu"));
		int length = 1353;
		List<String> result = cityMapper.getZipCodeList();
		assertEquals(length, result.size());
	}

	@Test
	public void testGetCityInfo() {
		System.out.println("getCityInfo");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpapu"));
		String expectedZip = "4400";
		String expectedName = "Kalundborg";

		CityInfoDTO cityInfoDTO = cityMapper.getCity(expectedZip);
		assertEquals(expectedZip, cityInfoDTO.getZipCode());
		assertEquals(expectedName, cityInfoDTO.getCity());
	}

	@Test
	public void testAddCity() {
		System.out.println("addCity");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpapu"));
		CityInfo city = new CityInfo();
		city.setCity("test");
		city.setZipCode("99999");

		cityMapper.addCity(city);

		assertEquals("test", cityMapper.getCity("99999").getCity());
	}

	@Test
	public void testRemoveCity() {
		System.out.println("RemoveCity");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpapu"));
		assertEquals("test", cityMapper.getCity("99999").getCity());
		cityMapper.removeCity("99999");

		assertEquals(null, cityMapper.getCity("99999"));
	}

	@Test
	public void testEditCity() {
		System.out.println("EditCity");
		CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpapu"));
		CityInfoDTO cityInfoDTO = cityMapper.getCity("TEST");
		cityInfoDTO.setCity("TESTBY");
		cityMapper.editCity(cityInfoDTO);

		assertEquals("TESTBY", cityMapper.getCity("TEST").getCity());

	}

}
