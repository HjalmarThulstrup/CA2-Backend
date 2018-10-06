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
import exceptions.HobbyNotFoundException;
import exceptions.HobbyWrongFormatException;
import facade.AddressFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("address")
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
     *
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id") int id) throws AddressNotFoundException {
        AddressDTO result = null;
        try {
            result = af.getAddress(id);
        } catch (Exception e) {
            throw new AddressNotFoundException("No address with id " + id);
        }
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(result)).build();
    }

    @POST
    @Path("/createAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAddress(String content) throws AddressWrongFormatException {
        Address a = gson.fromJson(content, Address.class);
        AddressDTO createdAddress = null;
        try {
            createdAddress = af.createAddress(a);
        } catch (Exception e) {
            throw new AddressWrongFormatException("Address could not be created, check address info...");
        }

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(createdAddress)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddress(@PathParam("id") int id) throws AddressNotFoundException {
        AddressDTO deletedAddress = null;
        try {
            deletedAddress = af.deleteAddress(id);
        } catch (Exception e) {
            throw new AddressNotFoundException("Couldn't find an address with the id of: " + id);
        }

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(deletedAddress)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAddress(String content, @PathParam("id") int id) throws AddressNotFoundException {
        Address newAddress = gson.fromJson(content, Address.class);
        Address savedAddress = null;
        try {
            savedAddress = new Address(af.getAddress(id));
        } catch (Exception e) {
            throw new AddressNotFoundException("No address with id: " + id);
        }

        if (newAddress.getStreet() != null) {
            savedAddress.setStreet(newAddress.getStreet());
        }
        if (newAddress.getAdditionalInfo() != null) {
            savedAddress.setAdditionalInfo(newAddress.getAdditionalInfo());
        }
        if (newAddress.getCityInfo() != null) {
            savedAddress.setCityInfo(newAddress.getCityInfo());
        }
        if (newAddress.getPersonList() != null) {
            savedAddress.setPersonList(newAddress.getPersonList());
        }
        AddressDTO result = af.editAddress(savedAddress);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(result)).build();
    }
}
