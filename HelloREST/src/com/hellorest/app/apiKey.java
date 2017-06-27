package com.hellorest.app;

public class apiKey {
	
	public static String getSecret() {
		// the secret is always in even numbers, if you use an odd length secret, the last character is ignored
		return "12345678";
	}

}
