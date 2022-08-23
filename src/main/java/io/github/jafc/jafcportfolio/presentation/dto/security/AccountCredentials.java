package io.github.jafc.jafcportfolio.presentation.dto.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class AccountCredentials implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
}
