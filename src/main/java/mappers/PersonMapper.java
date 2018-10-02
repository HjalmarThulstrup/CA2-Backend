/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.PersonDTO;
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

    public static Person getByPhone(int phone)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpapu");
        EntityManager em = emfactory.createEntityManager();

        TypedQuery<Person> q = em.createNamedQuery("Person.findByPhone", Person.class);
        q.setParameter("phoneNumber", phone);

        Person pers = q.getResultList().get(0);

        em.close();
        emfactory.close();

        return pers;
    }

    public static void deletePersonById(int id)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpapu");
        EntityManager em = emfactory.createEntityManager();

        Person person = em.find(Person.class, id);

        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();

        em.close();
        emfactory.close();
    }

    public static Person editPerson(Person person)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpapu");
        EntityManager em = emfactory.createEntityManager();

        Person personFromDB = em.find(Person.class, person.getId());
        em.getTransaction().begin();

        personFromDB = person;
        em.persist(personFromDB);

        em.getTransaction().commit();

        em.close();
        emfactory.close();

        return person;
    }

    public static List<Person> getPeopleByHobby(Hobby hobby)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpapu");
        EntityManager em = emfactory.createEntityManager();

        List<Person> pList = em.createQuery("SELECT p FROM Person p INNER JOIN p.hobbyList h WHERE h.id = :hobbyId", Person.class).setParameter("hobbyId", hobby.getId()).getResultList();

        em.close();
        emfactory.close();
        
        return pList;
    }

    public static List<Person> getPeopleByCity(CityInfo city)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpapu");
        EntityManager em = emfactory.createEntityManager();

        List<Person> pList = em.createQuery("SELECT p FROM Person p INNER JOIN p.address.cityInfo ci WHERE ci.zipCode = :zip", Person.class).setParameter("zip", city.getZipCode()).getResultList();

        em.close();
        emfactory.close();
        
        return pList;    }

    public static Person createPerson(Person person)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpapu");
        EntityManager em = emfactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(person);

        em.getTransaction().commit();

        em.close();
        emfactory.close();

        return person;    
    }
}
