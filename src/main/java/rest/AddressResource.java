/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.AddressDTO;
import DTO.HobbyDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Address;
import entity.Hobby;
import exceptions.AddressNotFoundException;
import exceptions.AddressWrongFormatException;
import exceptions.HobbyWrongFormatException;
import facade.AddressFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mappers.AddressMapper;

/**
 * REST Web Service
 *
 * @author juanni420
 */
@Path("Address")
public class AddressResource {

    @Context
    private UriInfo context;
    AddressFacade af;
    Gson gson;

    /**
     * Creates a new instance of AddressResource
     */
    public AddressResource() {
        af = new AddressFacade(new AddressMapper(Persistence.createEntityManagerFactory("jpapu")));
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of rest.AddressResource
     * @return an instance of Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
            .entity(gson.toJson(af.getAddress(0))).build();
    }
    
    /**
     * Retrieves representation of an instance of rest.AddressResource
     * @return an instance of Response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id") int id) throws AddressNotFoundException  {
        AddressDTO result = af.getAddress(id);
        if (result == null) {
            throw new AddressNotFoundException("No address with id " + id);
        }
        return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
            .entity(gson.toJson(af.getAddress(0))).build();
    }
    
    @POST
    @Path("/createAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAddress(String content) throws AddressWrongFormatException  {
        Address a = gson.fromJson(content, Address.class);
        AddressDTO createdAddress = af.createAddress(a);
        
        if (createdAddress.equals(null)) {
            throw new AddressWrongFormatException("Address could not be created, check address info...");
        }
        
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(createdAddress)).build();
    }
    

    /**
     * PUT method for updating or creating an instance of AddressResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
