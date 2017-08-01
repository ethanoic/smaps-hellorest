package com.hellorest.managers;

import com.hellorest.data.*;

public class SubscribersManager {
	
	private SubscribersDataLayer dataLayer = new SubscribersDataLayer();
	
	public long RegisterSubscriber(String email, String password, String name) {
		if (!dataLayer.IsEmailUsed(email) && email != "" && password != "" && name != "") {
			long id = dataLayer.Create(email, password, name);
			// may do other things - like send a welcome or confirmation email
			return id;
		} else
			return 0;
	}
	
	public Boolean IsSubscriberValid(String email, String password) {
		Boolean result = dataLayer.IsEmailPasswordExists(email, password);
		return result;
	}
	
	public Boolean IsEmailExists(String email) {
		return dataLayer.IsEmailUsed(email);
	}
}
