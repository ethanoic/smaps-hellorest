package com.hellorest.models.api;

public class RegisterSubscriberModel {
	public String Email;
	public String Password;
	public String Name;
	
	public RegisterSubscriberModel(String Email, String Password, String Name) {
		this.Email = Email;
		this.Password = Password;
		this.Name = Name;
	}
}
