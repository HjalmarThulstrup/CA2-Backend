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

/**
 *
 * @author martin
 */
public class CityMapper {

	private EntityManagerFactory emf;

	public CityMapper(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<CityInfo> getZipCodeList() {
		EntityManager em = emf.createEntityManager();
		List<CityInfo> cityInfos = null;

		try {
			em.getTransaction().begin();
			String qString = "SELECT c FROM CityInfo c";
			cityInfos = em.createNamedQuery("CityInfo.findAll").getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cityInfos;
	}

	public CityInfo addCity(CityInfo city) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(city);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return city;
	}

	public CityInfo getCity(String zipCode) {
		EntityManager em = emf.createEntityManager();
		CityInfo cityInfo = null;
		try {
			em.getTransaction().begin();
			String qString = "SELECT c FROM CityInfo AS c WHERE c.zipCode = :zipCode";
			cityInfo = em.createQuery(qString, CityInfo.class).setParameter("zipCode", zipCode).getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cityInfo;
	}

	public CityInfo removeCity(String zipCode) {
		EntityManager em = emf.createEntityManager();
		CityInfo cityInfo = null;

		cityInfo = em.find(CityInfo.class, zipCode);
		em.getTransaction().begin();
		em.remove(cityInfo);
		em.getTransaction().commit();

		em.close();

		return cityInfo;
	}

	public CityInfo editCity(CityInfoDTO city) {
		EntityManager em = emf.createEntityManager();
		CityInfo cityInfo = null;
		try {
			em.getTransaction().begin();
			cityInfo = em.find(CityInfo.class, city.getZipCode());
			cityInfo.setCity(city.getCity());
			em.merge(cityInfo);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cityInfo;
	}

}
