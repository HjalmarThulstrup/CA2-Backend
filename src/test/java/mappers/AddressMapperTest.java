/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.AddressDTO;
import DTO.CityInfoDTO;
import entity.Address;
import entity.CityInfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juanni420
 */
public class AddressMapperTest {

    AddressMapper addrMapper = new AddressMapper(Persistence.createEntityManagerFactory("jpaputest"));

    public AddressMapperTest() {
    }

    /**
     * Test of createAddress method, of class AddressMapper.
     */
    @Test
    public void testCreateAddress() {
        System.out.println("createAddress");

        Address address = new Address();
        address.setStreet("Tester Road");
        address.setCityInfo(new CityInfo("1000", "Testby"));
        address.setAdditionalInfo("test");

        AddressDTO result = new AddressDTO(addrMapper.createAddress(address));

        assertEquals("Tester Road, test", result.getStreetAndInfo());
        assertEquals("1000 Testby", result.getCityInfo());
    }

    /**
     * Test of getAddress method, of class AddressMapper.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
          assertEquals("Tester Road, test", new AddressDTO(addrMapper.getAddress(2)).getStreetAndInfo());
    }
    
    /**
     * Test of editAddress method, of class AddressMapper.
     */
    @Test
    public void testEditAddress() {
        System.out.println("editAddress");

        Address address = new Address();
        address.setStreet("Tester Edit Road");
        address.setAdditionalInfo("test");
        address.setCityInfo(new CityInfo("1000", "TestEditby"));

        addrMapper.createAddress(address);

        assertEquals("Tester Edit Road, test", new AddressDTO(addrMapper.getAddress(3)).getStreetAndInfo());
        address.setStreet("Edited Road");
        addrMapper.editAddress(address);
        assertEquals("Edited Road, test", new AddressDTO(addrMapper.getAddress(3)).getStreetAndInfo());
    }
   

    /**
     * Test of deleteAddress method, of class AddressMapper.
     */
    @Test
    public void testDeleteAddress() {
        System.out.println("deleteAddress");

        int id = 2;
        assertEquals("Tester Road, test", new AddressDTO(addrMapper.getAddress(id)).getStreetAndInfo());
        addrMapper.deleteAddress(id);
        assertEquals(null, addrMapper.getAddress(id));
    }

}
