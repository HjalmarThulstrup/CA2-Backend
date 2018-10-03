/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entity.Hobby;
import DTO.HobbyDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rlumh
 */
public class HobbyMapper {
    
    EntityManagerFactory emf;

    public HobbyMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Adds a hobby to the database
     * @param hobby
     * @return 
     */
    public HobbyDTO addHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();

            return new HobbyDTO(hobby);
        } catch(Exception e){
            //Skal nok kastes en custom exception her
        } finally {
            em.close();
        }
        
        return null;
        
    }

    public HobbyDTO deleteHobby(int id) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            Hobby h = em.find(Hobby.class, id);
            em.remove(h);
            em.getTransaction().commit();


            return new HobbyDTO(h);
        } catch(Exception e){
            //Skal nok kastes en custom exception her
        } finally {
            em.close();
        }
        
        return null;
    }

    public HobbyDTO editHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            Hobby h = em.find(Hobby.class, hobby.getId());
            if(h != null) {
                h = hobby;
                em.merge(h);
                em.getTransaction().commit();
                return new HobbyDTO(h);
            }
            
        } catch(Exception e){
            //Skal nok kastes en custom exception her
        } finally {
            em.close();
        }
        
        return null;
    }

    public int getHobbyPopularity(Integer id) {
        EntityManager em = getEntityManager();
        
        try {
            Query q = em.createNamedQuery("Hobby.getHobbyPopulation");
            q.setParameter("id", id);
            
            return q.getFirstResult();
            
        } catch(Exception e){
            //Skal nok kastes en custom exception her
            e.printStackTrace();
        } finally {
            em.close();
        }
        return 0;
    }


    public HobbyDTO getHobby(Integer id) {
        EntityManager em = getEntityManager();
        String qString = "SELECT NEW DTO.HobbyDTO(h) From Hobby AS h WHERE h.id = :id";
        try {
            em.getTransaction().begin();
            TypedQuery<HobbyDTO> q = em.createQuery(qString, HobbyDTO.class);
            q.setParameter("id", id);
            
            HobbyDTO hobby = q.getSingleResult();

            return hobby;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }
}
