package com.hellorest.api;

import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.security.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.hellorest.models.*;

@Path("books")
public class BooksService {
	
	public static List<Book> books = new ArrayList<Book>();
	
	public BooksService() {
		books = new ArrayList<Book>();
		
		// CONTENT NEGOTIATION
		/*
		books.add(new Book("Outliers", "Malcom Gladwell"));
		books.add(new Book("The Art of SaaS", "Dr. Ahmed Bouzid; David Rennyson"));
		books.add(new Book("Sapiens", "Yuval Noah Harari"));
		books.add(new Book("Dune", "Frank Herbet"));
		*/
	
		// HATEOAS
		
		books.add(new Book(1, "Outliers", "Malcom Gladwell", 
				new ResourceLink("/books/1", "GET", "Book Details")));
		books.add(new Book(2, "The Art of SaaS", "Dr. Ahmed Bouzid; David Rennyson", 
				new ResourceLink("/books/2", "GET", "Book Details")));
		books.add(new Book(3, "Sapiens", "Yuval Noah Harari", 
				new ResourceLink("/books/3", "GET", "Book Details")));
		books.add(new Book(4, "Dune", "Frank Herbet", 
				new ResourceLink("/books/3", "GET", "Book Details")));
				
	}
	
	@GET
	@Produces({"application/json; qs=1","application/xml; qs=0.9"})
	public Response GetBooks() {
		GenericEntity< List<Book> > entity = new GenericEntity< List<Book> >(this.books) {};
		
		return Response.ok(entity).build();
	}
	
	
	@GET
	@Produces({"application/json; qs=1","application/xml; qs=0.9"})
	@Path("{id}")
	public Response GetBookById(@PathParam("id") int id) {
		Book book = null;
		
		try {
			book = this.books.stream()
						.filter(t -> t.Id == id)
						.findFirst()
						.get();
		} catch (NoSuchElementException e) {
			
		}
		GenericEntity< Book > entity = new GenericEntity< Book >(book) {};
		
		return Response.ok(entity).build();
	}
	
	@PermitAll
	@GET
	@Produces({"application/json"})
	@Path("search")
	public Response SearchBooks(@QueryParam("search") String search) {
		return Response.ok().build();
	}
	
	@DenyAll
	@DELETE
	@Produces({"application/json"})
	@Path("deleteall")
	public Response DeleteAllBooks(@QueryParam("search") String search) {
		return Response.ok().build();
	}
	
	@RolesAllowed({"USER", "ADMIN"})
	@POST
	@Produces({"application/json"})
	@Path("draft")
	public Response AddDraft() {
		return Response.ok().build();
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Produces({"application/json"})
	@Path("approve")
	public Response ApproveBook() {
		return Response.ok().build();
	}
	
}
