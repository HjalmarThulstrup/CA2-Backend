/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.PersonDTO;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import exceptions.PersonNotFoundException;
import interfaces.PersonFacadeInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import mappers.PersonMapper;

/**
 *
 * @author Wicktus
 */
public class PersonFacade implements PersonFacadeInterface
{

    private final PersonMapper mapper;

    public PersonFacade(String persistenceName)
    {
        this.mapper = new PersonMapper(persistenceName);
    }

    @Override
    public PersonDTO createPerson(Person person)
    {
        return new PersonDTO(mapper.createPerson(person));
    }

    @Override
    public PersonDTO deletePerson(int id)
    {
        try {
            return new PersonDTO(mapper.deletePersonById(id));
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public PersonDTO editPerson(Person person)
    {
        return new PersonDTO(mapper.editPerson(person));
    }

    @Override
    public PersonDTO getPerson(Phone phone)
    {
        try {
            return new PersonDTO(mapper.getByPhone(phone.getNumber()));
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<PersonDTO> getPeopleByHobby(Hobby hobby)
    {
        try {
            return mapper.getPeopleByHobby(hobby).stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PersonDTO> getPeopleByCity(CityInfo city)
    {
        try {
            return mapper.getPeopleByCity(city).stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public PersonDTO getPersonById(int id)
    {
        try {
            return new PersonDTO(mapper.getById(id));
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<PersonDTO> getAllPeople()
    {
        try {
            return mapper.getAllpeople().stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
