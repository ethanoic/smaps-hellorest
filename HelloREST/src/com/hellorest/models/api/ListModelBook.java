package com.hellorest.models.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.hellorest.models.ResourceLink;

@XmlRootElement
public class ListModelBook {
	
	@XmlElement
	public long Id;
	
	@XmlElement
	public String Title;
	@XmlElement
	public int AuthorId;
	@XmlElement
	public String CoverImage;
	
	public ResourceLink Link;
	
	public ListModelBook() {
		
	}
	
	public ListModelBook(long Id, String Title, int AuthorId, String CoverImage, ResourceLink ResourceLink) {
		this.Id = Id;
		this.Title = Title;
		this.AuthorId = AuthorId;
		this.CoverImage = CoverImage;
		this.Link = ResourceLink;
	}
}
