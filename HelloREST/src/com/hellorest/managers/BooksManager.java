package com.hellorest.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

import com.hellorest.data.BooksDataLayer;
import com.hellorest.models.Book;
import com.hellorest.models.Paging;
import com.hellorest.models.ResourceLink;
import com.hellorest.models.api.ListModelBook;

public class BooksManager {
	
	private BooksDataLayer dataLayer = new BooksDataLayer();
	
	// always add as draft not approved
	public Book AddBook(String title, String isbn, String exerpt, 
			int publishedYear, String publisher, String category, String coverImage,
			String authorName, Date authorDateOfBirth, String authorCountry ) {
		Book book = null;
		int authorId = 0;
		
		// add author
		
		// add book
		long bookId = dataLayer.AddBook(new Book(title, authorId, isbn, exerpt, 
				publishedYear, publisher, false, category, coverImage ));
		
		// return book
		book = dataLayer.GetBookById(bookId);
		
		return book;
	}
	
	public void ApproveBook(long id) {
		Book book = dataLayer.GetBookById(id);
		// there may be other processes to do so we get the book full details, e.g. send email to inform users
		book.Approved = true;
		dataLayer.UpdateBook(book);
	}
	
	public void RejectBook(long id) {
		Book book = dataLayer.GetBookById(id);
		// there may be other processes to do so we get the book full details, e.g. send email to inform users
		book.Approved = false;
		dataLayer.UpdateBook(book);
	}
	
	public Book GetBook(long id) {
		Book book = dataLayer.GetBookById(id);
		return book;
	}
	
	public void UpdateBook(long id, String title, String isbn, String exerpt, String publisher, int publisherYear, String category) {
		Book book = dataLayer.GetBookById(id);
		book.Title = title;
		book.ISBN = isbn;
		book.Exerpt = exerpt;
		book.Publisher = publisher;
		book.Category = category;
		book.PublishedYear = publisherYear;
		dataLayer.UpdateBook(book);
	}
	
	public void DeleteBook(long id) {
		dataLayer.DeleteBook(id);
	}
	
	public ArrayList<ListModelBook> GetAllBooks(int page, int pageSize) {
		ArrayList<ListModelBook> booksList = new ArrayList<ListModelBook>();
		ArrayList<Book> books = dataLayer.GetAllBooks(page, pageSize);
		for(Book book : books) {
			booksList.add(new ListModelBook(book.Id, book.Title, book.AuthorId, book.CoverImage, new ResourceLink()));
		}
		return booksList;
	}
	
	public ArrayList<Book> SearchBooksByDateAdded(Date start, Date end) {
		return dataLayer.SearchByDateAdded(start, end);
	}
	
	// TODO : write a method to return total number of pages
	public int GetTotalPages(int pagesize) {
		return 0;
	}
}
