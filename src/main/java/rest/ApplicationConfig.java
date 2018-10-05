/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author martin
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	/**
	 * Do not modify addRestResourceClasses() method.
	 * It is automatically populated with
	 * all resources defined in the project.
	 * If required, comment out calling this method in getClasses().
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
            resources.add(exceptions.AddressNotFoundExceptionMapper.class);
            resources.add(exceptions.AddressWrongFormatExceptionMapper.class);
            resources.add(exceptions.CityNotFoundExceptionMapper.class);
            resources.add(exceptions.HobbyNotFoundExceptionMapper.class);
        resources.add(rest.AddressResource.class);
        resources.add(rest.CityInfoResource.class);
        resources.add(rest.HobbyResource.class);
        resources.add(rest.PersonResource.class);
	}
	
}
