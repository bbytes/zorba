package com.bbytes.zorba.domain.mongo;

import lombok.Data;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bbytes.zorba.domain.IUser;


@Document
@Data
public class User extends Entity implements IUser{

	private static final long serialVersionUID = 6512152541161579617L;

	@Indexed
	private String userName;
	
	@Indexed
	private String email;
	
	private String password;
}
