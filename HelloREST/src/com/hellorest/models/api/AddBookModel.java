package com.hellorest.models.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class AddBookModel {

	@XmlElement
	public String Title;
	//setTitle()
	@XmlElement
	public String AuthorName;
	@XmlElement
	public Date AuthorDateOfBirth;
	@XmlElement
	public String AuthorCountry;
	@XmlElement
	public String ISBN;
	@XmlElement
	public String Exerpt;
	@XmlElement
	public int PublishedYear;
	@XmlElement
	public String Publisher;
	@XmlElement
	public String Category;

	@XmlElement
	public String CoverImage;
	
	public AddBookModel() {
		
	}
	
	public AddBookModel(String Title, String AuthorName, Date AuthorDateOfBirth, String AuthorCountry, 
			String ISBN, String Exerpt, int PublishedYear, String Publisher, String Category, String coverImage) {
		this.Title = Title;
		this.AuthorName = AuthorName;
		this.AuthorDateOfBirth = AuthorDateOfBirth;
		this.AuthorCountry = AuthorCountry;
		this.ISBN = ISBN;
		this.PublishedYear = PublishedYear;
		this.Publisher = Publisher;
		this.Category = Category;
		this.CoverImage = CoverImage;
	}
}
