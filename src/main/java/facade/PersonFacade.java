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
import interfaces.PersonFacadeInterface;
import java.util.List;
import java.util.stream.Collectors;
import mappers.PersonMapper;

/**
 *
 * @author Wicktus
 */
public class PersonFacade implements PersonFacadeInterface
{

    @Override
    public PersonDTO createPerson(Person person)
    {
        return new PersonDTO(PersonMapper.createPerson(person));
    }

    @Override
    public void deletePerson(int id)
    {
        PersonMapper.deletePersonById(id);
    }

    @Override
    public PersonDTO editPerson(Person person)
    {
        return new PersonDTO(PersonMapper.editPerson(person));
    }

    @Override
    public PersonDTO getPerson(Phone phone)
    {
        Person person = PersonMapper.getByPhone(phone.getNumber());
        return new PersonDTO(person);
    }

    @Override
    public List<PersonDTO> getPeopleByHobby(Hobby hobby)
    {
        return PersonMapper.getPeopleByHobby(hobby).stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
    }

    @Override
    public List<PersonDTO> getPeopleByCity(CityInfo city)
    {
        return PersonMapper.getPeopleByCity(city).stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
    }

}
