/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.CityInfoDTO;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Wicktus
 */
public class PersonMapper
{

    private EntityManagerFactory emfactory;

    public PersonMapper(String persistenceName)
    {
        this.emfactory = Persistence.createEntityManagerFactory(persistenceName);
    }

    public Person getById(int id)
    {
        EntityManager em = emfactory.createEntityManager();

        Person person = em.find(Person.class, id);
        em.close();

        return person;
    }

    public Person getByPhone(int phone)
    {
        EntityManager em = emfactory.createEntityManager();

        TypedQuery<Person> q = em.createNamedQuery("Person.findByPhone", Person.class);
        q.setParameter("phoneNumber", phone);

        Person pers = q.getResultList().get(0);
        em.close();

        return pers;
    }

    public Person deletePersonById(int id)
    {
        EntityManager em = emfactory.createEntityManager();

        Person person = em.find(Person.class, id);

        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();

        em.close();

        return person;
    }

    public Person editPerson(Person person)
    {
        EntityManager em = emfactory.createEntityManager();

        em.getTransaction().begin();
        Person personFromDB = em.find(Person.class, person.getId());
        personFromDB = person;
        
        em.merge(personFromDB);

        em.getTransaction().commit();
        em.close();

        return person;
    }

    public List<Person> getPeopleByHobby(Hobby hobby)
    {
        EntityManager em = emfactory.createEntityManager();

        List<Person> pList = em.createQuery("SELECT p FROM Person p INNER JOIN p.hobbyList h WHERE h.id = :hobbyId", Person.class).setParameter("hobbyId", hobby.getId()).getResultList();

        em.close();

        return pList;
    }

    public List<Person> getPeopleByCity(CityInfo city)
    {
        EntityManager em = emfactory.createEntityManager();

        List<Person> pList = em.createQuery("SELECT p FROM Person p INNER JOIN p.address.cityInfo ci WHERE ci.zipCode = :zip", Person.class).setParameter("zip", city.getZipCode()).getResultList();
        em.close();

        return pList;
    }

    public List<Person> getPeopleByCity(CityInfoDTO city)
    {
        EntityManager em = emfactory.createEntityManager();

        List<Person> pList = em.createQuery("SELECT p FROM Person p INNER JOIN p.address.cityInfo ci WHERE ci.zipCode = :zip", Person.class).setParameter("zip", city.getZipCode()).getResultList();
        em.close();

        return pList;
    }

    public Person createPerson(Person person)
    {
        EntityManager em = emfactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(person);

        em.getTransaction().commit();
        em.close();

        return person;
    }
}
