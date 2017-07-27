package com.hellorest.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	@XmlElement
	public int Id;
	
	@XmlElement
	public String Body;
	
	@XmlElement
	public String Subject;
	
	@XmlElement
	public Date DateSent;
	
	public int SubscriberId;
	
	public Message() {
	}
	
	public Message(int Id, String subject, String body, Date dateSent, int subscriberId) {
		this.Id = Id;
		this.Subject = subject;
		this.Body = body;
		this.DateSent = dateSent;
		this.SubscriberId = subscriberId;
	}
}
