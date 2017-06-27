package com.hellorest.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	@XmlElement
	public int Id;
	@XmlElement
	public String Title;
	@XmlElement
	public String Author;
		public Book(String title, String author) {
		this.Title = title;
		this.Author = author;
	}

	
	@XmlElement
	public ResourceLink Uri;
	
	public Book() {
		this.Uri = new ResourceLink();
	}
	
	public Book(int id, String title, String author, ResourceLink uri) {
		this.Id = id;
		this.Title = title;
		this.Author = author;
		this.Uri = uri;
	}
	
}
