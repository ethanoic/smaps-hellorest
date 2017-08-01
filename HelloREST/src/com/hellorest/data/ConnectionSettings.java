package com.hellorest.data;

public class ConnectionSettings {

	private static String connstring = "jdbc:mysql://localhost:3306/hellorest2?useSSL=false";
	private static String user = "root";
	private static String password = "1234";
	
	public static String getConnString() {
		return connstring;
	}
	
	public static String getUser() {
		return user;
	}
	
	public static String getPassword() {
		return password;
	}
}
