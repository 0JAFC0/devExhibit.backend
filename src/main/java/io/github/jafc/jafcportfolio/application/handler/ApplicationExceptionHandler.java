package io.github.jafc.jafcportfolio.application.handler;

import io.github.jafc.jafcportfolio.infrastructure.exceptions.ExceptionResponse;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.InvalidJwtAuthenticationException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@ControllerAdvice //utilizamos sempre que precisamos juntar um tratamento que esta espalhados entre os controllers
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handlerNotFoundExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> handlerInvalidJwtAuthenticationExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(IntrospectionException.class)
	public final ResponseEntity<ExceptionResponse> handlerInvalidJwtAuthenticationExceptions(IntrospectionException ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handlerAllExceptions(Exception ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvocationTargetException.class)
	public final ResponseEntity<ExceptionResponse> handlerInvalidJwtAuthenticationExceptions(InvocationTargetException ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalAccessException.class)
	public final ResponseEntity<ExceptionResponse> handlerInvalidJwtAuthenticationExceptions(IllegalAccessException ex, WebRequest req) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
