package com.hellorest.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 Book Schema
* for MYSQL 5 
CREATE TABLE `books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `authorId` bigint(20) NOT NULL DEFAULT '0',
  `isbn` varchar(500) NOT NULL,
  `exerpt` varchar(500) NOT NULL,
  `publishedYear` int(11) NOT NULL,
  `publisher` varchar(500) NOT NULL,
  `category` varchar(500) NOT NULL,
  `approved` bit(1) NOT NULL,
  `dateAdded` datetime DEFAULT NULL,
  `coverImage` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
);


 */

@XmlRootElement
public class Book {

	@XmlElement
	public long Id;
	@XmlElement
	public String Title;
	@XmlElement
	public int AuthorId;
	@XmlElement
	public Date DateAdded;
	@XmlElement
	public String ISBN;
	@XmlElement
	public String Exerpt;
	@XmlElement
	public int PublishedYear;
	@XmlElement
	public String Publisher;
	@XmlElement
	public Boolean Approved;
	@XmlElement
	public String Category; //this would be better if its stored in a category table/object seperately
	@XmlElement
	public String CoverImage; 
	
	public Book() {
		
	}
	public Book(String title, int authorId, String ISBN, String Exerpt, 
			int PublishedYear, String Publisher, Boolean Approved, String Category, String CoverImage ) {
		this.Title = title;
		this.AuthorId = authorId;
		this.DateAdded = DateAdded;
		this.ISBN = ISBN;
		this.Exerpt = Exerpt;
		this.PublishedYear = PublishedYear;
		this.Publisher = Publisher;
		this.Approved = Approved;
		this.Category = Category;
		this.CoverImage = CoverImage;
	}
	
	public Book(long Id, String title, int authorId, Date DateAdded, String ISBN, String Exerpt, 
			int PublishedYear, String Publisher, Boolean Approved, String Category, String CoverImage  ) {
		this.Id = Id;
		this.Title = title;
		this.AuthorId = authorId;
		this.DateAdded = DateAdded;
		this.ISBN = ISBN;
		this.Exerpt = Exerpt;
		this.PublishedYear = PublishedYear;
		this.Publisher = Publisher;
		this.Approved = Approved;
		this.Category = Category;
		this.CoverImage = CoverImage;
	}
	
	
	
}
