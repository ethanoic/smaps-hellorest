package com.hellorest.managers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.hellorest.data.*;

public class SubscribersManager {
	
	private SubscribersDataLayer dataLayer = new SubscribersDataLayer();
	
	public String encrypt(String text) {
		String encrypted = "";
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");

		    md.update(text.getBytes());

		    byte byteData[] = md.digest();

		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < byteData.length; i++)
		        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    
		    encrypted = sb.toString();
		    //System.out.println("Digest(in hex format):: " + sb.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return encrypted;
	}
	
	public long RegisterSubscriber(String email, String password, String name) {
		if (!dataLayer.IsEmailUsed(email) && email != "" && password != "" && name != "") {
			long id = dataLayer.Create(email, password, name);
			// may do other things - like send a welcome or confirmation email
			return id;
		} else
			return 0;
	}
	
	public long RegisterSubscriber(String email, String password, String name, String tel) {
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
