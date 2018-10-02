/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTO.PersonDTO;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.List;

/**
 *
 * @author wicktus
 */
public interface PersonFacadeInterface {
        
    //PersonDTO add/delete/edit/get
    public PersonDTO createPerson(Person person);
    
    public void deletePerson(int id);
    
    public PersonDTO editPerson(Person person);
    
    public PersonDTO getPerson(Phone phone);
    
    public List<PersonDTO> getPeopleByHobby(Hobby hobby);
    
    public List<PersonDTO> getPeopleByCity(CityInfo city);
}
