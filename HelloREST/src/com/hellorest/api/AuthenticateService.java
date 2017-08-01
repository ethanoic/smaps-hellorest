package com.hellorest.api;

import javax.annotation.security.PermitAll;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.DatatypeConverter;

import com.hellorest.app.JWTToken;
import com.hellorest.app.jwtIssuer;
import com.hellorest.managers.SubscribersManager;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;   

@Path("authenticate")
public class AuthenticateService {
	
	private SubscribersManager manager = new SubscribersManager();
	/*
	POST authenticate/token
	 * */
	@PermitAll
	@POST
	@Path("token")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response GenerateAuthToken(
			@FormParam("username") @DefaultValue("user") String username, 
			@FormParam("password") @DefaultValue("password") String password) {
		//System.out.println(username + "," + password);
		// supposed to validate user and password against database, if successful then generate token, else return 401 forbidden
		// if user password is valid, get the role and store in the payload as a claim
		
		String role = "USER"; //may be stored at db per user to allow dynamic control of user role
		
		String name = ""; // may also retrieve user details or other information required for future api calls
		/*
		if (username.equals("smap") && password.equals("password")) {
			role = "ADMIN";
		}
		*/

		if (manager.IsSubscriberValid(username, password)) {
			String token = JWTToken.createJWT(jwtIssuer.getId(), jwtIssuer.getIssuer(), "books token", "payload", role, name, 36000);
			return Response.ok(token).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
		
	} 

}
