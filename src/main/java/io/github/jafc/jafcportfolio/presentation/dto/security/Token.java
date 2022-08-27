package io.github.jafc.jafcportfolio.presentation.dto.security;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String email;
	private String type;
	private String token;
}
