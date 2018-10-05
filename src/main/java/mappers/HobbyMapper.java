/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entity.Hobby;
import DTO.HobbyDTO;
import exceptions.HobbyNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rlumh
 */
public class HobbyMapper
{

    EntityManagerFactory emf;

    public HobbyMapper(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    /**
     * Adds a hobby to the database
     *
     * @param hobby
     * @return HobbyDTO
     */
    public Hobby addHobby(Hobby hobby)
    {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();

            return hobby;
        } catch (Exception e) {
            //Skal nok kastes en custom exception her
        } finally {
            em.close();
        }

        return null;

    }

    public Hobby deleteHobby(int id)
    {
        EntityManager em = getEntityManager();
        Hobby h = null;
        try {
            h = em.find(Hobby.class, id);
            em.getTransaction().begin();
            em.remove(h);
            em.getTransaction().commit();

        } catch (Exception e) {
            //Skal nok kastes en custom exception her
            e.printStackTrace();
        } finally {
            em.close();
        }

        return h;
    }

    public Hobby editHobby(Hobby hobby)
    {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Hobby h = em.find(Hobby.class, hobby.getId());
            if (h != null) {
                h = hobby;
                em.merge(h);
                em.getTransaction().commit();
                return h;
            }

        } catch (Exception e) {
            //Skal nok kastes en custom exception her
        } finally {
            em.close();
        }

        return null;
    }

    public int getHobbyPopularity(Integer id)
    {
        EntityManager em = getEntityManager();
        int res = 0;
        try {
            Query q = em.createNamedQuery("Hobby.getHobbyPopulation");
            q.setParameter("id", id);

            res = ((Number)q.getSingleResult()).intValue();

        } catch (Exception e) {
            //Skal nok kastes en custom exception her
            e.printStackTrace();
        } finally {
            em.close();
        }
        return res;
    }

    public Hobby getHobby(Integer id)
    {
        EntityManager em = getEntityManager();
        Hobby hobby = null;
        try {
            em.getTransaction().begin();
            hobby = em.createNamedQuery("Hobby.findById", Hobby.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return hobby;
    }
    
    public List<Hobby> getHobby() {
        EntityManager em = getEntityManager();
        List<Hobby> hobbys;
        try {
            em.getTransaction().begin();
            hobbys = em.createNamedQuery("Hobby.findAll", Hobby.class).getResultList();
            return hobbys;
        } catch (Exception e) {
            
        } finally {
            em.close();
        }
        return null;
    }
}
