package com.hellorest.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/*
create table subscribers (
	id bigint not null auto_increment primary key,
    email varchar(500),
    `password` varchar(500),
    `name` varchar(500),
    dateRegistered datetime not null
);
 */
public class SubscribersDataLayer {
	private String connstring = ConnectionSettings.getConnString();
	private String user = ConnectionSettings.getUser();
	private String password = ConnectionSettings.getPassword();
	
	public long Create(String email, String userPassword, String name) {
		long Id = 0;
		try (
			// get sql connections
			Connection conn = DriverManager.getConnection(connstring, user, password);
			PreparedStatement statement = conn.prepareStatement("INSERT INTO subscribers (email, password, name, dateRegistered) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		) {
			statement.setString(1, email);
			statement.setString(2, userPassword);
			statement.setString(3, name);
			statement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Creating subscriber failed, no rows affected.");
	        }
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	Id = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating subscriber failed, no ID obtained.");
	            }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Id;
	}
	
	public Boolean IsEmailPasswordExists(String email, String userPassword) {
		Boolean isExist = false;
		try (
				// get sql connections
				Connection conn = DriverManager.getConnection(connstring, user, password);
				PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM subscribers WHERE email = ? and password = ?");
			) {
				statement.setString(1, email);
				statement.setString(2, userPassword);
				
				ResultSet rs = statement.executeQuery();
				if (rs.next())
					isExist = rs.getInt(1) > 0;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isExist;
	}
	
	public Boolean IsEmailUsed(String email) {
		Boolean isExist = false;
		try (
				// get sql connections
				Connection conn = DriverManager.getConnection(connstring, user, password);
				PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM subscribers WHERE email = ?");
			) {
				statement.setString(1, email);
				
				ResultSet rs = statement.executeQuery();
				if (rs.next())
					isExist = rs.getInt(1) > 0;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isExist;
		
	}
}
