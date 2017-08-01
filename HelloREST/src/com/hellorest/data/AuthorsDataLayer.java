package com.hellorest.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/*

create table authors (
	id bigint not null auto_increment primary key,
    `name` varchar(500),
    dateOfBirth datetime not null,
    country varchar(500)
);

 */
public class AuthorsDataLayer {
	private String connstring = ConnectionSettings.getConnString();
	private String user = ConnectionSettings.getUser();
	private String password = ConnectionSettings.getPassword();
	
	//public void Update
	
	public long Create(String name, String country, Date dateOfBirth) {
		long Id = 0;
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("INSERT INTO authors (name, country, dateOfBirth) "
					+ "VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		) {
			statement.setString(1, name);
			statement.setString(2, country);
			statement.setDate(3, new java.sql.Date(new Date().getTime()));
			
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Creating author failed, no rows affected.");
	        }
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	Id = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating author failed, no ID obtained.");
	            }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Id;
	}
}
