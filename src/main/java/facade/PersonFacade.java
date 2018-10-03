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
    private final PersonMapper mapper;

    public PersonFacade()
    {
        this.mapper = new PersonMapper("jpapu");
    }

    @Override
    public PersonDTO createPerson(Person person)
    {
        return new PersonDTO(mapper.createPerson(person));
    }

    @Override
    public PersonDTO deletePerson(int id)
    {
        return new PersonDTO(mapper.deletePersonById(id));
    }

    @Override
    public PersonDTO editPerson(Person person)
    {
        return new PersonDTO(mapper.editPerson(person));
    }

    @Override
    public PersonDTO getPerson(Phone phone)
    {
        return new PersonDTO(mapper.getByPhone(phone.getNumber()));
    }

    @Override
    public List<PersonDTO> getPeopleByHobby(Hobby hobby)
    {
        return mapper.getPeopleByHobby(hobby).stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
    }

    @Override
    public List<PersonDTO> getPeopleByCity(CityInfo city)
    {
        return mapper.getPeopleByCity(city).stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
    }

}
