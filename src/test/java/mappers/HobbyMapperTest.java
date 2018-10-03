/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.HobbyDTO;
import entity.Hobby;
import entity.Person;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rlumh
 */
public class HobbyMapperTest {
    HobbyMapper hm = new HobbyMapper(Persistence.createEntityManagerFactory("jpaputest"));
    
    public HobbyMapperTest() {
    }
    
    @Before
    public void setUp() {

    }

    @Test
    public void testAddHobby() {
        System.out.println("addHobby");
        Hobby h = new Hobby("TestHobby", "TestDesc");
        
        HobbyDTO result = hm.addHobby(h);
        
        
        assertNotNull(result);
        assertEquals(result.getName(), h.getName());
        assertEquals(result.getDesc(), h.getDescription());
        


    }
    

    @Test
    public void testEditHobby() {
        //add hobby to the db
        HobbyDTO newHobby = hm.addHobby(new Hobby("EditHobbyTest", "EditHobbyTestDescription"));
        
        //Change the hobbyDTO and convert to hobby
        newHobby.setDesc("THIS IS AN EDITED DESCRIPTION");
        HobbyDTO editedHobby = hm.editHobby(new Hobby(newHobby));
        
        //Test to see id the object updated
        assertEquals("THIS IS AN EDITED DESCRIPTION", editedHobby.getDesc());
        assertEquals(newHobby.getId(), editedHobby.getId());
        assertEquals(newHobby.getDesc(), editedHobby.getDesc());

        
    }

    //NEEED SOME WORK!!!!
    @Test
    public void testGetHobbyPopularity() {
        final int actualNumberOfPeople = 3;
        //Hobby hobby = new Hobby("testPop", "TestPopDesc");
        Integer id = 2;
        HobbyDTO h = hm.getHobby(id);
        System.out.println(h.getId());
        assertNotNull(h);

        System.out.println(hm.getHobbyPopularity(id));
        
        
       //assertTrue(actualNumberOfPeople == result);
        
    }

    @Test
    public void testGetHobby() {
        System.out.println("getHobby");
        final Integer id = 2;
        HobbyDTO hobby = hm.getHobby(id);
        assertNotNull(hobby);
        assertEquals("getTESTDESC", hobby.getDesc());
        assertTrue(id == hobby.getId());
    }
    
    
    @Test
    public void testDeleteHobby() {
        System.out.println("deleteHobby");
        
        Hobby h = new Hobby("TestHobby1", "TestDesc1");
        
        //Make new hobby and add it to db
        HobbyDTO newHobby = hm.addHobby(h);
        //make sure the new hobby in db exist and is the same as the one i just made
        assertNotNull(newHobby);
        HobbyDTO hobbyFromDb = hm.getHobby(newHobby.getId());
        assertEquals(newHobby.getName(), hobbyFromDb.getName());
        //Delete the hooby and it should return the hobby that is deleted
        hm.deleteHobby(hobbyFromDb.getId());
        
        //try to find the hobby i just deleted, should return null if it doesn't exist
        //HobbyDTO deletedHobby = hm.getHobby(hobbyFromDb.getId());
        //assertNull(deletedHobby);
        
        
        //Try to delete something that doesn't exist
        HobbyDTO hobby = hm.deleteHobby(2000);
        assertNull(hobby);
    }
}
