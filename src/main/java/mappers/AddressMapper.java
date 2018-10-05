/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entity.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanni420
 */
public class AddressMapper
{

    private EntityManagerFactory emf;

    public AddressMapper(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public Address createAddress(Address addr)
    {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(addr);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        return addr;
    }

    public Address deleteAddress(int id)
    {
        EntityManager em = getEntityManager();
        try {
            Address address = em.find(Address.class, id);
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return address;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        return null;
    }

    public Address editAddress(Address addr)
    {
        EntityManager em = getEntityManager();
        try {
            Address address = em.find(Address.class, addr.getId());
            em.getTransaction().begin();
            address = addr;
            em.merge(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return addr;
    }

    public Address getAddress(int id)
    {
        EntityManager em = getEntityManager();
        Address addr = null;
        try {
            em.getTransaction().begin();
            addr = em.createNamedQuery("Address.findById", Address.class).setParameter("id", id).getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        
        return addr;
    }

}
