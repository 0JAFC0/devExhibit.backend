package io.github.jafc.jafcportfolio.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExistException(String exception) {
        super(exception);
    }
}
