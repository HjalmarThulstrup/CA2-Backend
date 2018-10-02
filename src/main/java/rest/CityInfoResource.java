/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.CityFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
		return Response.ok().entity(gson.toJson(cityFacade.getZipCodeList())).build();
	}

}
