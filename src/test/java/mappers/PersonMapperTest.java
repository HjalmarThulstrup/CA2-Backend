/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.CityInfoDTO;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import exceptions.PersonNotFoundException;
import facade.CityFacade;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author martin
 */

public class PersonMapperTest
{

    private PersonMapper mapper;

    public PersonMapperTest()
    {
        mapper = new PersonMapper("jpaputest");
    }

    /**
     * Test of getByPhone method, of class PersonMapper.
     * @throws exceptions.PersonNotFoundException
     */
    @Test
    public void testGetByPhone() throws PersonNotFoundException
    {
        System.out.println("getByPhone");

        Person expResult = mapper.getById(1);

        List<Phone> phone = expResult.getPhoneList();

        Person result = mapper.getByPhone(phone.get(0).getNumber());

        assertEquals(expResult, result);
    }

    /**
     * Test of deletePersonById method, of class PersonMapper.
     * @throws exceptions.PersonNotFoundException
     */
    @Test
    public void testDeletePersonById() throws PersonNotFoundException
    {
        System.out.println("deletePersonById");
        int id = 3;

        Person expResult = mapper.getById(id);
        Person pers = mapper.deletePersonById(id);

        //Checks if the deleted person and the pulled person with matching ID is the same. This works because removing the person from the database returns it.
        assertEquals(expResult, pers);
    }

    /**
     * Test of editPerson method, of class PersonMapper.
     * @throws exceptions.PersonNotFoundException
     */
    @Test
    public void testEditPerson() throws PersonNotFoundException
    {
        System.out.println("editPerson");

        String expResult = "Dinkos";
        
        Person person = mapper.getById(1);
        person.setLastName(expResult);
        
        Person result = mapper.editPerson(person);

        Person editedPerson = mapper.getById(1);
        
        assertEquals(expResult, editedPerson.getLastName());
    }

    /**
     * Test of getPeopleByHobby method, of class PersonMapper.
     * @throws exceptions.PersonNotFoundException
     */
    @Test
    public void testGetPeopleByHobby() throws PersonNotFoundException
    {
        System.out.println("getPeopleByHobby");

        Person expResult = mapper.getById(1);
        Hobby hobby = expResult.getHobbyList().get(0);

        List<Person> result = mapper.getPeopleByHobby(hobby);

        //Tests if the person used to get the hobby is in the result of people.
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of getPeopleByCity method, of class PersonMapper.
     * @throws exceptions.PersonNotFoundException
     */
    @Test
    public void testGetPeopleByCity() throws PersonNotFoundException
    {
        System.out.println("getPeopleByCity");
		CityFacade cityFacade = new CityFacade(new CityMapper(Persistence.createEntityManagerFactory("jpaputest")));
        CityInfoDTO city = cityFacade.getCity("1000");

        Person expResult = mapper.getById(1);

        List<Person> result = mapper.getPeopleByCity(city);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of createPerson method, of class PersonMapper.
     */
    @Test
    public void testCreatePerson()
    {
        System.out.println("createPerson");
        Person expResult = new Person("mailtest@testmail.dk", "Testo", "Von Test", null, null, null);

        Person result = mapper.createPerson(expResult);

        assertEquals(expResult, result);
    }

}
