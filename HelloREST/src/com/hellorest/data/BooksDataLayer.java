package com.hellorest.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Date;

import com.hellorest.models.Book;
import com.hellorest.models.Paging;
import com.hellorest.models.ResourceLink;
import com.hellorest.models.api.ListModelBook;

/*

create table books (
	id bigint not null auto_increment primary key,
    title nvarchar(500) not null,
    authorId bigint not null,
    isbn nvarchar(500) not null,
    exerpt nvarchar(500) not null,
    publishedYear int not null,
    publisher nvarchar(500) not null,
    category nvarchar(500) not null,
    approved bit not null,
    dateAdded datetime not null,
    coverImage nvarchar(500) not null
);

 */
public class BooksDataLayer {
	
	private String connstring = "jdbc:mysql://localhost:3306/hellorest2?useSSL=false";
	private String user = "root";
	private String password = "1234";
	
	public ArrayList<Book> GetAllBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM books");
		) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getLong("id"), rs.getString("title"), rs.getInt("authorId"), 
									rs.getDate("dateAdded"), rs.getString("isbn"), rs.getString("exerpt"), 
									rs.getInt("publishedYear"), rs.getString("publisher"), rs.getBoolean("approved"), 
									rs.getString("category"), rs.getString("coverImage")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return books;
	}
	
	public long CountBooks() {
		long count = 0;
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("SELECT COUNT(id) FROM books");
		) {
			ResultSet rs = statement.executeQuery();
			count = rs.getLong(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	/*
	public Dictionary<String, Object> GetAllBooks(int page, int pageSize) {
		Dictionary<String, Object> booksPaged = new Hashtable<String, Object>();
		ArrayList<Book> books = new ArrayList<Book>();
		Paging paging = new Paging();
		booksPaged.put("data", books);
		booksPaged.put("paging", paging);
		
		int offset = pageSize * page;
		
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM books LIMIT ? OFFSET ?");
		) {
			statement.setInt(1, pageSize);
			statement.setInt(2, offset);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getLong("id"), rs.getString("title"), rs.getInt("authorId"), 
									rs.getDate("dateAdded"), rs.getString("isbn"), rs.getString("exerpt"), 
									rs.getInt("publishedYear"), rs.getString("publisher"), rs.getBoolean("approved"), 
									rs.getString("category")));
			}
			booksPaged.put("data", books);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return booksPaged;
	}
	*/
	
	public ArrayList<Book> GetAllBooks(int page, int pageSize) {
		ArrayList<Book> books = new ArrayList<Book>();
		int offset = pageSize * page;
		
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM books LIMIT ? OFFSET ?");
		) {
			statement.setInt(1, pageSize);
			statement.setInt(2, offset);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getLong("id"), rs.getString("title"), rs.getInt("authorId"), 
									rs.getDate("dateAdded"), rs.getString("isbn"), rs.getString("exerpt"), 
									rs.getInt("publishedYear"), rs.getString("publisher"), rs.getBoolean("approved"), 
									rs.getString("category"), rs.getString("coverImage")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return books;
	}
	
	public ArrayList<Book> SearchByDateAdded(Date start, Date end) {
		ArrayList<Book> result = new ArrayList<Book>();
		
		try (
				// get sql connections
				Connection conn = DriverManager.getConnection(connstring, user, password);
				//PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE dateAdded >= ? AND dateAdded <= ?");
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE dateAdded BETWEEN ? AND ?");
			) {
				statement.setDate(1, new java.sql.Date(start.getTime()));
				statement.setDate(2, new java.sql.Date(end.getTime()));
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					result.add(new Book(rs.getLong("id"), rs.getString("title"), rs.getInt("authorId"), 
										rs.getDate("dateAdded"), rs.getString("isbn"), rs.getString("exerpt"), 
										rs.getInt("publishedYear"), rs.getString("publisher"), rs.getBoolean("approved"), 
										rs.getString("category"), rs.getString("coverImage")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
		return result;
	}
	
	public long AddBook(Book newBook) {
		long bookId = 0;
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("INSERT INTO books (title, authorId, isbn, exerpt, publishedYear, publisher, approved, category, dateAdded, coverImage) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		) {
			statement.setString(1, newBook.Title);
			statement.setInt(2, newBook.AuthorId);
			statement.setString(3, newBook.ISBN);
			statement.setString(4, newBook.Exerpt);
			statement.setInt(5, newBook.PublishedYear);
			statement.setString(6, newBook.Publisher);
			statement.setBoolean(7, newBook.Approved);
			statement.setString(8, newBook.Category);
			statement.setDate(9, new java.sql.Date(new Date().getTime()));
			statement.setString(10, newBook.CoverImage);
			
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Creating book failed, no rows affected.");
	        }
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	bookId = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating book failed, no ID obtained.");
	            }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookId;
	}
	
	public Book GetBookById(long id) {
		Book getBook = null;
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
		) {
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				getBook = new Book(rs.getLong("id"), rs.getString("title"), rs.getInt("authorId"), 
						rs.getDate("dateAdded"), rs.getString("isbn"), rs.getString("exerpt"), 
						rs.getInt("publishedYear"), rs.getString("publisher"), rs.getBoolean("approved"), 
						rs.getString("category"), rs.getString("coverImage"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getBook;
	}
	
	public void UpdateBook(Book book) {
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("UPDATE books SET title = ?, author = ?, isbn = ?, exerpt = ?, publishedYear = ?, publisher = ?, category = ?, approved = ? WHERE id = ?");
		) {
			statement.setString(1, book.Title);
			statement.setInt(2, book.AuthorId);
			statement.setString(3, book.ISBN);
			statement.setString(4, book.Exerpt);
			statement.setInt(5, book.PublishedYear);
			statement.setString(6, book.Publisher);
			statement.setBoolean(7, book.Approved);
			statement.setString(8, book.Category);
			
			statement.setLong(9, book.Id);
			
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Updating book failed, no rows affected.");
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void DeleteBook(long id) {
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("DELETE FROM books WHERE id = ?");
		) {
			statement.setLong(1, id);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Deleting book failed, no rows affected.");
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
