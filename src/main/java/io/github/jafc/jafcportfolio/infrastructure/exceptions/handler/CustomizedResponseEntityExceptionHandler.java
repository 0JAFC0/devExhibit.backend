package io.github.jafc.jafcportfolio.infrastructure.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.jafc.jafcportfolio.infrastructure.exceptions.ExceptionResponse;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.InvalidJwtAuthenticationException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;

@ControllerAdvice //utilizamos sempre que precisamos juntar um tratamento que esta espalhados entre os controllers
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handlerAllExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handlerNotFoundExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> handlerInvalidJwtAuthenticationExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ExceptionResponse> handlerIllegalArgumentsExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), "algum argumento obrigatorio foi passado como nulo.", req.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
}
