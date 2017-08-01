package com.hellorest.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subscriber {
	@XmlElement
	private long Id;
	public void setId(long id) {
		Id = id;
	}
	public long getId() {
		return Id;
	}
	@XmlElement
	private String Email; //used as username
	public void setEmail(String email) {
		Email = email;
	}
	public String getEmail() {
		return Email;
	}
	@XmlElement
	private String Password;
	public void setPassword(String password) {
		Password = password;
	}
	public String getPassword() {
		return Password;
	}
	@XmlElement
	private String Name;
	public void setName(String name) {
		Name = name;
	}
	public String getName() {
		return Name;
	}
	@XmlElement
	private Date DateRegistered;
	public void setDateRegistered(Date dateReg) {
		DateRegistered = dateReg;
	}
	public Date getDateRegistered() {
		return DateRegistered;
	}
	
	public Subscriber() {
		
	}
	
	public Subscriber(int Id, String Name, String Email, String Password, Date DateAdded) {
		this.Id = Id;
		this.Name = Name;
		this.Email = Email;
		this.Password = Password;
		this.DateRegistered = DateAdded;
	}
}
