/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author juanni420
 */
public class AddressWrongFormatExceptionMapper implements ExceptionMapper<AddressWrongFormatException>{

    @Context
    ServletContext context;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
    @Override
    public Response toResponse(AddressWrongFormatException e) {
        boolean isDebug = context.getInitParameter("debug").equals("true");
		ExceptionDTO err = new ExceptionDTO(e, 500, isDebug);	

		return Response.status(500)
				.header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(err))
				.type(MediaType.APPLICATION_JSON).
				build();
    }
}
