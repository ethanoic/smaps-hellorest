package com.hellorest.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author {
	
	@XmlElement
	public long Id;
	
	@XmlElement
	public String Name;
	
	@XmlElement
	public Date DateOfBirth;
	
	@XmlElement
	public String Country;
	
	public Author() {
	}
	
	public Author(int Id, String Name, Date DateOfBirth, String Country) {
		this.Id = Id;
		this.Name = Name;
		this.DateOfBirth = DateOfBirth;
		this.Country = Country;
	}
}
