/*
4 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.PersonDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import exceptions.PersonNotFoundException;
import facade.PersonFacade;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Wicktus
 */
@Path("person")
public class PersonResource
{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PersonFacade personFacade = new PersonFacade("jpapu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource()
    {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @param id
     * @return an instance of java.lang.String
     * @throws exceptions.PersonNotFoundException
     */
    @GET
    @Path("/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonById(@PathParam("id") int id) throws PersonNotFoundException
    {
        PersonDTO person = personFacade.getPersonById(id);
        if (person == null) {
            throw new PersonNotFoundException("No person with ID: " + id);
        }
        return Response.ok().entity(gson.toJson(person)).build();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     * @throws exceptions.PersonNotFoundException
     */
    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() throws PersonNotFoundException
    {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(personFacade.getAllPeople())).build();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     * @throws exceptions.PersonNotFoundException
     */
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactInfoJson() throws PersonNotFoundException
    {
        List<PersonDTO> pList = personFacade.getAllPeople();
        String contactJson = pList.stream()
                .map(p -> "{\"email\":\""
                + p.getEmail()
                + "\", \"phoneNums\": "
                + gson.toJson(p.getPhoneNums())
                + "}")
                .collect(Collectors.toList()).toString();

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(contactJson).build();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @param id
     * @return an instance of java.lang.String
     * @throws exceptions.PersonNotFoundException
     */
    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactInfoJsonById(@PathParam("id") int id) throws PersonNotFoundException
    {
        PersonDTO p = personFacade.getPersonById(id);

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity("{\"email\":\"" + p.getEmail() + "\",\"phoneNums\": " + gson.toJson(p.getPhoneNums()) + "}").build();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @param hobbyId
     * @param id
     * @return an instance of java.lang.String
     * @throws exceptions.PersonNotFoundException
     */
    @GET
    @Path("/hobby/{hobbyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleJsonByHobby(@PathParam("hobbyId") int hobbyId) throws PersonNotFoundException
    {
        List<PersonDTO> p = personFacade.getPeopleByHobby(new Hobby(hobbyId));

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(p)).build();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @param zipCode
     * @return an instance of java.lang.String
     * @throws exceptions.PersonNotFoundException
     */
    @GET
    @Path("/city/{zipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleJsonByCity(@PathParam("zipCode") String zipCode) throws PersonNotFoundException
    {
        List<PersonDTO> p = personFacade.getPeopleByCity(new CityInfo(zipCode));

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(p)).build();
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     * @param id
     * @return
     * @throws exceptions.PersonNotFoundException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(String content, @PathParam("id") int id) throws PersonNotFoundException
    {
        Person newPerson = gson.fromJson(content, Person.class); //hvad er best practice her?
        PersonDTO savedPerson = personFacade.getPersonById(id);
        if (savedPerson == null) {
            throw new PersonNotFoundException("No person with id: " + id);
        }

        PersonDTO editedPerson = personFacade.editPerson(newPerson);
        return Response.ok().entity(gson.toJson(editedPerson)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String content)
    {
        Person newPerson = gson.fromJson(content, Person.class);
        System.out.println("newPerson: " + newPerson);
        personFacade.createPerson(newPerson);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .entity(gson.toJson(newPerson)).build();
    }
//Udkommenteret fordi den gav mig fejl -Martin
// Det fint. Den var ikke færdig -David
//	/**
//	 * PUT method for updating or creating an instance of PersonResource
//	 *
//	 * @param content representation for the resource
//	 * @param id
//	 */
//	@PUT
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response putJson(String content, @PathParam("id") int id) throws PersonNotFoundException {
//		PersonDTO newPerson = gson.fromJson(content, PersonDTO.class);
//		PersonDTO savedPerson = personFacade.getPersonById(id);
//		if (savedPerson == null) {
//			throw new PersonNotFoundException("No person with id: " + id);
//		}
//
//		PersonDTO editedPerson = personFacade.editPerson(newPerson);
//		return Response.ok().entity(gson.toJson(editedPerson)).build();
//
//	}

}
