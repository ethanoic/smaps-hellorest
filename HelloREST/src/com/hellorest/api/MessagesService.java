package com.hellorest.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import com.hellorest.models.*;

@Path("messages")
public class MessagesService {

	@GET
	public Response Get() {
		return Response.ok("get Hello").build();
	}
	
	@POST
	@Consumes("text/plain")
	public Response Post(String message) {
		return Response.ok("you posted " + message).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes("text/plain")
	public Response Put(String message, @PathParam("id") int id) {
		return Response.ok("you put " + message + " at " + id).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response Delete(@PathParam("id") int id) {
		return Response.ok("you deleted " + id).build();
	}
	
	@GET
	@Path("{id}")
	public Response GetWithPathParam(@PathParam("id") int id) {
		return Response.ok("get with path parameter id:" + id).build();
	}
	
	// GET /messages/query?term=
	@GET
	@Path("query")
	public Response GetWithQueryParam(
			@DefaultValue("[empty]") @QueryParam("term") String term) {
		return Response.ok("get with querystring:" + term).build();
	}
	
	// GET /messages/json
	@GET
	@Path("json")
	@Produces("application/json")
	public Response GetJSONEntity() {
		
		Message message = new Message();
		message.Subject = "a message subject";
		message.Body = "the body";
		message.DateSent = new Date();
		
		GenericEntity<Message> entity = new GenericEntity<Message>(message) {};
		
        return Response.ok(entity).build();
	}

	// GET /messages/list
	@GET
	@Path("list")
	@Produces("application/json")
	public Response GetJSONList() {
		
		// Instantiate an Array List that will store your message objects
		List<Message> messages = new ArrayList<Message>();
		
		Message message = new Message();
		message.Subject = "subject 1";
		message.Body = "the body 1";
		message.DateSent = new Date();

		messages.add(message);
		
		Message message2 = new Message();
		message2.Subject = "subject 2";
		message2.Body = "the body 2";
		message2.DateSent = new Date();

		messages.add(message2);
		
		GenericEntity< List<Message> > entity = new GenericEntity< List<Message> >(messages) {};
		
        return Response.ok(entity).build();
	}
	
}
