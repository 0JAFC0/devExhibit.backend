package io.github.jafc.jafcportfolio.presentation.dto.security;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountCredentials implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
}
