package com.hellorest.api;

import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hellorest.managers.SubscribersManager;
import com.hellorest.models.api.RegisterSubscriberModel;

@Path("subscribers")
public class SubscribersService {
	
	private SubscribersManager manager = new SubscribersManager(); 
			
	@PermitAll
	@POST
	public Response Register(@FormParam("email") String email,
							@FormParam("password") String password,
							@FormParam("name") String name) {
		long id = manager.RegisterSubscriber(email, password, name);
		
		System.out.println(email + "," + password + "," + name);
		if (id == 0) 
			//return Response.status(Status.CONFLICT).build();
			return Response.ok("Email already exists").build();
		else
			return Response.status(Status.CREATED).build();
	}
	/*
	@Path("v2")
	@PermitAll
	@POST
	public Response RegisterUsingJson(RegisterSubscriberModel user) {
		long id = manager.RegisterSubscriber(user.Email, user.Password, user.Name);

		if (id == 0) 
			//return Response.status(Status.CONFLICT).build();
			return Response.ok("Email already exists").build();
		else
			return Response.status(Status.CREATED).build();
	}
	*/
}
