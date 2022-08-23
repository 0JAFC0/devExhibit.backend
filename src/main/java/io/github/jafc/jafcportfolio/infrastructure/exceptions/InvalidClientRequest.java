package io.github.jafc.jafcportfolio.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidClientRequest extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidClientRequest(String message) {
		super(message);
	}
}
