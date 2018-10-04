/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.HobbyDTO;
import facade.HobbyFacade;
import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Hobby;
import exceptions.HobbyNotFoundException;
import exceptions.HobbyWrongFormatException;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author rlumh
 */
@Path("Hobby")
public class HobbyResource {
    
    @Context
    private UriInfo context;
    private HobbyFacade hf;
    private Gson gson;
    /**
     * Creates a new instance of HobbyResource
     */
    public HobbyResource() {
        hf = new HobbyFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of rest.HobbyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
            .entity(gson.toJson(hf.getHobby())).build();
    }

    /**
     * Returns a hobby with the specific id from db
     * @param id
     * @return HobbyDTO
     * @throws HobbyNotFoundException 
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHobby(@PathParam("id") int id) throws HobbyNotFoundException {
        HobbyDTO result = hf.getHobby(id);
        if (result == null) {
            throw new HobbyNotFoundException("No Hobby with id: " + id);
        }
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(result)).build();
    }       
    
    /**
     * PUT method for updating or creating an instance of HobbyResource
     * @param content representation for the resource
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putHobby(String content, @PathParam("id") int id) throws HobbyNotFoundException {
        Hobby newHobby = gson.fromJson(content, Hobby.class);
        Hobby savedHobby = new Hobby(hf.getHobby(id));
        
        if(savedHobby == null)
            throw new HobbyNotFoundException("no Hobby with id: "+ id);
        if (newHobby.getName() != null) {
            savedHobby.setName(newHobby.getName());
        }
        if (newHobby.getDescription() != null) {
            savedHobby.setDescription(newHobby.getDescription());
        }
        if (newHobby.getPersonList() != null) {
            savedHobby.setPersonList(newHobby.getPersonList());
        }
        HobbyDTO result = hf.editHobby(savedHobby);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(result)).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHobby(@PathParam("id") int id) throws HobbyNotFoundException {
        HobbyDTO h = hf.deleteHobby(id);
        if (h == null) {
            throw new HobbyNotFoundException("Couldn't find Hobby with the id of: " + id);
        }
        
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(h)).build();
    }
    
    @POST
    @Path("/createHobby")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHobby(String content) throws HobbyWrongFormatException {
        Hobby h = gson.fromJson(content, Hobby.class);
        
        HobbyDTO createdHobby = hf.addHobby(h);
        if(createdHobby.equals(null)) {
            throw new HobbyWrongFormatException("Hobby could not be created, check Hobby info...");
        }
        
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(createdHobby)).build();
    }
}
