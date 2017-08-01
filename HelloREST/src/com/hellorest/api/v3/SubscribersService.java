package com.hellorest.api.v3;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hellorest.managers.SubscribersManager;
import com.hellorest.models.api.RegisterSubscriberModel;

@Path("v3/subscribers")
public class SubscribersService {
	
	private SubscribersManager manager = new SubscribersManager(); 
	
	@RolesAllowed("ADMIN")
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
}