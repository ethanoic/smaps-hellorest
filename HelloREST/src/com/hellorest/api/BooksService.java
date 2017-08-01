package com.hellorest.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.security.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.hellorest.managers.BooksManager;
import com.hellorest.models.Book;
import com.hellorest.models.Paging;
import com.hellorest.models.ResourceLink;
import com.hellorest.models.api.AddBookModel;
import com.hellorest.models.api.BooksPagedModel;
import com.hellorest.models.api.ListModelBook;
import com.hellorest.models.api.UpdateBookModel;

@Path("books")
public class BooksService {
	
	private String resourcePath = "/HelloREST/books";
	//private BooksDataLayer data = new BooksDataLayer();
	private BooksManager manager = new BooksManager();
	
	public BooksService() {
	}
	/*
	@PermitAll
	@GET
	@Produces({"application/json; qs=1","application/xml; qs=0.9"})
	public Response GetBooks() {
		GenericEntity< List<ListModelBook> > entity = new GenericEntity< List<ListModelBook> >(this.manager.GetAllBooks()) {};
		
		return Response.ok(entity).build();
	}
	*/
	@PermitAll
	@GET
	@Produces({"application/json; qs=1","application/xml; qs=0.9"})
	public Response GetBooksPaged(@QueryParam("page") int page, @QueryParam("pagesize") int pagesize) {
		
		ArrayList<Book> books = new ArrayList<Book>();
		//Dictionary<String, Object> booksPaged = new Hashtable<String, Object>();
		BooksPagedModel booksPaged = new BooksPagedModel();
		Paging paging = new Paging();
		//booksPaged.put("paging", paging);
		booksPaged.Paging = paging;
		
		// get total pages count
		int totalPages = manager.GetTotalPages(pagesize);
		// create individual page resource links, depends on how many pages there are
		for(int p = 0; p < totalPages; p++) {
			paging.Pages.add(new ResourceLink(resourcePath + "?page=" + p + "&pagesize=" + pagesize, "GET", "Page"));
		}
		
		// create next page
		if (page < totalPages) {
			int nextPage = page + 1;
			paging.Next.Href = resourcePath + "?page=" + nextPage + "&pagesize=" + pagesize;
			paging.Next.Method = "GET";
			paging.Next.Rel = "NextPage";
		}
		
		// create prev page
		if (page > 0) {
			int prevPage = page - 1;
			paging.Prev.Href = resourcePath + "?page=" + prevPage + "&pagesize=" + pagesize;
			paging.Prev.Method = "GET";
			paging.Prev.Rel = "NextPage";
		}
		
		ArrayList<ListModelBook> booksList = this.manager.GetAllBooks(page, pagesize);
		for(ListModelBook book : booksList) {
			book.Link.Href = resourcePath + "/" + book.Id;
			book.Link.Method = "GET";
			book.Link.Rel = "Details";
		}
		//booksPaged.put("data", booksList);
		booksPaged.Data = booksList;
		
		//GenericEntity< Dictionary<String, Object> > entity = new GenericEntity< Dictionary<String, Object> >(booksPaged) {};
		GenericEntity< BooksPagedModel > entity = new GenericEntity< BooksPagedModel >(booksPaged) {};
		
		//return Response.ok(entity.getEntity().get("data")).build();
		return Response.ok(entity).build();
	}
	
	@PermitAll
	@Path("search-date")
	@GET
	@Produces({"application/json; qs=1","application/xml; qs=0.9"})
	public Response GetBooksByDateAdded(@QueryParam("start") Date start, @QueryParam("end") Date end) {
		ArrayList<Book> result = this.manager.SearchBooksByDateAdded(start, end);
		GenericEntity < ArrayList<Book> > entity = new GenericEntity< ArrayList<Book> >(result) {};
		return Response.ok(entity).build();
	}
	
	@PermitAll
	@GET
	@Produces({"application/json; qs=1","application/xml; qs=0.9"})
	@Path("{id}")
	public Response GetBookById(@PathParam("id") long id) {
		Book book = this.manager.GetBook(id);
		GenericEntity< Book > entity = new GenericEntity< Book >(book) {};
		
		return Response.ok(entity).build();
	}
	
	@RolesAllowed("USER")
	@Consumes({"application/json"})
	@POST
	public Response AddBook(AddBookModel book) {
		Book addedBook = this.manager.AddBook(book.Title, book.ISBN, book.Exerpt, 
				book.PublishedYear, book.Publisher,
				book.Category, book.CoverImage, book.AuthorName, book.AuthorDateOfBirth, 
				book.AuthorCountry );
		if (addedBook != null)
			return Response.ok(addedBook.Id).build();
		else
			return Response.noContent().build();
	}
	
	/*
	@PermitAll
	@POST
	public Response AddBook(AddBookModel book) {
		Book addedBook = this.manager.AddBook(book.Title, book.ISBN, book.Exerpt, 
				book.PublishedYear, book.Publisher,
				book.Category, book.AuthorName, book.AuthorDateOfBirth, 
				book.AuthorCountry);
		return Response.ok(addedBook.Id).build();
	}
	*/
	
	@PermitAll
	@Path("{id}")
	@PUT
	public Response UpdateBook(@PathParam("id") long id, UpdateBookModel book) {
		this.manager.UpdateBook(id, book.Title, book.ISBN, book.Exerpt, book.Publisher, book.PublishedYear, book.Category);
		return Response.ok().build();
	}
	
	@PermitAll
	@Path("{id}")
	@DELETE
	public Response DeleteBook(@PathParam("id") long id) {
		this.manager.DeleteBook(id);
		return Response.ok().build();
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
	
	@RolesAllowed("ADMIN")
	@POST
	@Produces({"application/json"})
	@Path("{id}/approve")
	public Response ApproveBook(@PathParam("id") long id) {
		this.manager.ApproveBook(id);
		return Response.ok().build();
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Produces({"application/json"})
	@Path("{id}/reject")
	public Response RejectBook(@PathParam("id") long id) {
		this.manager.RejectBook(id);
		return Response.ok().build();
	}
	
}
