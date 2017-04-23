package com.hellorest.models;

import java.util.Date;

public class Message {
	

	public String Body;
	

	public String Subject;
	

	public Date DateSent;
	
	public Message() {
	}
	
	public Message(String subject, String body, Date dateSent) {
		this.Subject = subject;
		this.Body = body;
		this.DateSent = dateSent;
	}
}
