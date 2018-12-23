package org.open.coffee.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@CustomQualifier
@Component
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest implements Rest {
	

	@Autowired
	private AppConfig config;

	@GET
	@Path("{id}")
	public String getUser(@PathParam("id") String id) {

		return "hello";
	}

}