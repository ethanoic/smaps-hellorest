package com.hellorest.data;
/*

create table messages (
	id bigint not null auto_increment primary key,
    bookId bigint not null,
    authorId bigint not null,
    `subject` nvarchar(1000) not null,
    body nvarchar(4000) not null,
    datePosted datetime not null
);

 */
public class MessagesDataLayer {
	private String connstring = ConnectionSettings.getConnString();
	private String user = ConnectionSettings.getUser();
	private String password = ConnectionSettings.getPassword();
}
