package com.hellorest.models;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Paging {
	public ResourceLink Next;
	public ResourceLink Prev;
	public ArrayList<ResourceLink> Pages;
	
	public Paging() {
		this.Next = new ResourceLink();
		this.Prev = new ResourceLink();
		this.Pages = new ArrayList<ResourceLink>();
	}
}
