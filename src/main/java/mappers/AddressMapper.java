/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.AddressDTO;
import DTO.CityInfoDTO;
import DTO.HobbyDTO;
import entity.Address;
import entity.CityInfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanni420
 */
public class AddressMapper {

    private EntityManagerFactory emf;

    public AddressMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AddressDTO createAddress(Address addr) {
        EntityManager em = getEntityManager();
        AddressDTO addressDTO = null;
        try {
            em.getTransaction().begin();
            em.persist(addr);
            em.getTransaction().commit();
            addressDTO = new AddressDTO(addr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            return addressDTO;
        }
    }

    public boolean deleteAddress(int id) {
        EntityManager em = getEntityManager();
        try {
            Address address = em.find(Address.class, id);
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public AddressDTO editAddress(Address addr) {
        EntityManager em = getEntityManager();
        AddressDTO addressDTO = null;
        try {
            Address address = em.find(Address.class, addr.getId());
            em.getTransaction().begin();
            address = addr;
            em.merge(address);
            em.getTransaction().commit();
            return addressDTO = new AddressDTO(address);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return null;
    }

    public AddressDTO getAddress(int id) {
        EntityManager em = getEntityManager();
        String qString = "SELECT NEW DTO.AddressDTO(a) From Address AS a WHERE a.id = :id";
        try {
            em.getTransaction().begin();
            TypedQuery<AddressDTO> q = em.createQuery(qString, AddressDTO.class);
            q.setParameter("id", id);
            
            AddressDTO addrDTO = q.getSingleResult();
            return addrDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            em.close();
        }
        return null;
    }

}
