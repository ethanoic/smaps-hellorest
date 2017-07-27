package com.hellorest.models.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateBookModel {

	@XmlElement
	public String Title;
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
	public String Category;
}
