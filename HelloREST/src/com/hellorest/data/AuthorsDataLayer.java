package com.hellorest.data;

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
}
