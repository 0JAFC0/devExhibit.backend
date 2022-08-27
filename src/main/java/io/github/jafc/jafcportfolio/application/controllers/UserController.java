package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.TokenService;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.UserRequest;
import io.github.jafc.jafcportfolio.presentation.dto.UserResponse;
import io.github.jafc.jafcportfolio.presentation.dto.security.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.security.Token;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.annotations.Api;

@Api(value = "End Point do usuario")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;
    
    @PostMapping("/user/register")
    public ResponseEntity<Response<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
        User convertido = modelMapperService.convert(userRequest, User.class);
        return responseService.create(modelMapperService.convert(userService.saveUser(convertido), UserResponse.class));
    }
    
    @PostMapping("/user/signin")
    public ResponseEntity<Token> signin(@RequestBody AccountCredentials accountCredentials) {
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(accountCredentials.getEmail(), accountCredentials.getPassword());
    	
    	Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    	
    	String token = tokenService.generateToken(authentication);
    	
    	return ResponseEntity.ok(Token.builder().email(accountCredentials.getEmail()).type("Bearer ").token(token).build());
    }

	@PutMapping("/user/update")
    public ResponseEntity<Response<UserResponse>> update(@RequestBody UserResponse userResponse) {
        return responseService.ok(modelMapperService.convert(userService.update(modelMapperService.convert(userResponse, User.class)), UserResponse.class));
    }

    @GetMapping("/users")
    public ResponseEntity<Response<List<User>>> getAll() {
        return responseService.ok(userService.getUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Response<User>> getById(@PathVariable Long id) {
        return responseService.ok(userService.findById(id));
    }
}
