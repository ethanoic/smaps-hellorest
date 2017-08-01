package com.hellorest.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	@XmlElement
	public long Id;
	
	@XmlElement
	public long BookId;
	
	@XmlElement
	public String Body;
	
	@XmlElement
	public String Subject;
	
	@XmlElement
	public Date DatePosted;
	
	public long SubscriberId;
	
	public Message() {
	}
	
	public Message(long Id, long bookId, String subject, String body, Date dateSent, long subscriberId) {
		this.Id = Id;
		this.BookId = bookId;
		this.Subject = subject;
		this.Body = body;
		this.DatePosted = dateSent;
		this.SubscriberId = subscriberId;
	}
}
