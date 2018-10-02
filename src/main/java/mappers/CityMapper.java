/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import DTO.CityInfoDTO;
import entity.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author martin
 */
public class CityMapper {

	private EntityManagerFactory emf;

	public CityMapper(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<CityInfoDTO> getZipCodeList() {
		EntityManager em = emf.createEntityManager();
		List<CityInfoDTO> zipCodes = null;

		try {
			em.getTransaction().begin();
			String qString = "SELECT NEW DTO.CityInfoDTO(c) From CityInfo AS c";

			TypedQuery<CityInfoDTO> q = em.createQuery(qString, CityInfoDTO.class);
			zipCodes = q.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
			return zipCodes;
		}
	}

	public CityInfoDTO addCity(CityInfo city) {
		EntityManager em = emf.createEntityManager();
		CityInfoDTO cityInfoDTO = null;

		try {
			em.getTransaction().begin();
			em.persist(city);
			em.getTransaction().commit();
			cityInfoDTO = new CityInfoDTO(city);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
			return cityInfoDTO;
		}
	}

	public CityInfoDTO getCity(String zipCode) {
		EntityManager em = emf.createEntityManager();
		CityInfoDTO cityInfoDTO = null;

		try {
			em.getTransaction().begin();
			String qString = "SELECT NEW DTO.CityInfoDTO(c) From CityInfo AS c WHERE c.zipCode = :zipCode";
			TypedQuery<CityInfoDTO> q = em.createQuery(qString, CityInfoDTO.class);
			q.setParameter("zipCode", zipCode);
			cityInfoDTO = q.getSingleResult();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
			return cityInfoDTO;
		}
	}

	public boolean removeCity(String id) {
		EntityManager em = emf.createEntityManager();

		try {
			CityInfo cityInfo = em.find(CityInfo.class, id);
			em.getTransaction().begin();
			em.remove(cityInfo);
			em.getTransaction().commit();
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			em.close();
		}
	}

	public CityInfoDTO editCity(CityInfoDTO city) {
		EntityManager em = emf.createEntityManager();
		CityInfoDTO cityInfoDTO = null;
		try {
			CityInfo cityInfo = em.find(CityInfo.class, city.getZipCode());
			em.getTransaction().begin();
			cityInfo.setCity(city.getCity());
			em.getTransaction().commit();
			cityInfoDTO = new CityInfoDTO(cityInfo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
			return cityInfoDTO;
		}
	}

}
