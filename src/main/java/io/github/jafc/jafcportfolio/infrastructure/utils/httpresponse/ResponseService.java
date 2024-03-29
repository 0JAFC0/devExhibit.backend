package io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.presentation.shared.Response;

@Service
public class ResponseService {
    
    private final MessageService messageService;

    public ResponseService(MessageService messageService) {
        this.messageService = messageService;
    }

    public <T> ResponseEntity<Response<T>> create(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<T>(data));
    }

    public <T> ResponseEntity<Response<T>> ok(T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<T>(data));
    }
    
    public <T> ResponseEntity<Response<T>> forbidden(T data) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<T>(data));
    }
    
    public <T> ResponseEntity<Response<T>> notFound(T data) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<T>(data));
    }
    
    public <T> ResponseEntity<Response<T>> unauthorized(T data) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response<T>(data));
    }
    
    public <T> ResponseEntity<Response<T>> badRequest(T data) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<T>(data));
    }
}
