package com.bbytes.zorba.domain;

import lombok.Data;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
public class User extends Entity {

	private static final long serialVersionUID = 6512152541161579617L;

	@Indexed
	private String userName;
	
	@Indexed
	private String email;
	
	private String password;
}
