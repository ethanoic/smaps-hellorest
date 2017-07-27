package com.hellorest.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subscriber {
	@XmlElement
	public int Id;
	@XmlElement
	public String Email; //used as username
	@XmlElement
	public String Password;
	@XmlElement
	public Date DateAdded;
	
	public Subscriber() {
		
	}
	
	public Subscriber(int Id, String Email, String Password, Date DateAdded) {
		this.Id = Id;
		this.Email = Email;
		this.Password = Password;
		this.DateAdded = DateAdded;
	}
}
