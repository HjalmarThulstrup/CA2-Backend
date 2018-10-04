/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.CityInfoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.CityInfo;
import exceptions.CityNotFoundException;
import facade.CityFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mappers.CityMapper;

/**
 * REST Web Service
 *
 * @author martin
 */
@Path("CityInfo")
public class CityInfoResource {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	CityMapper cityMapper = new CityMapper(Persistence.createEntityManagerFactory("jpapu"));
	CityFacade cityFacade = new CityFacade(cityMapper);
	@Context
	private UriInfo context;

	public CityInfoResource() {
	}

	@GET
	@Path("/zipCodeList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCities() {
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.entity(gson.toJson(cityFacade.getZipCodeList())).build();
	}

	@GET
	@Path("/{zipCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCity(@PathParam("zipCode") String zipCode) throws CityNotFoundException {
		CityInfoDTO cityInfoDTO = cityFacade.getCity(zipCode);
		if (cityInfoDTO == null) {
			throw new CityNotFoundException("No city with zipcode: " + zipCode);
		}
		return Response.ok().entity(gson.toJson(cityInfoDTO)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postCityInfo(String content) {
		CityInfo newCity = gson.fromJson(content, CityInfo.class);
		System.out.println("newCity: " + newCity);
		cityFacade.addCity(newCity);
		return Response.ok().entity(gson.toJson(newCity)).build();
	}

	@DELETE
	@Path("/{zipCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCity(@PathParam("zipCode") String zipCode) {
		return Response.ok().entity(gson.toJson(cityFacade.removeCity(zipCode))).build();
	}

	@PUT
	@Path("/{zipCode}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCity(String content, @PathParam("zipCode") String zipCode) throws CityNotFoundException {
		CityInfo newCity = gson.fromJson(content, CityInfo.class);
		CityInfoDTO savedCity = cityFacade.getCity(zipCode);
		if (savedCity == null) {
			throw new CityNotFoundException("No city with zipcode: " + zipCode);
		}
		cityFacade.editCity(newCity);
		return Response.ok().entity(gson.toJson(cityFacade.getCity(zipCode))).build();
	}

}
