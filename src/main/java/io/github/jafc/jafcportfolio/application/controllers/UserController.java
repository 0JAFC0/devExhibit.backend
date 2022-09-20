package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.request.UserRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import io.github.jafc.jafcportfolio.presentation.dto.response.UserResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.annotations.Api;

@Api(value = "End Point do usuario")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://jafc-backend.herokuapp.com/"})
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;
    
    @PostMapping("/user/signup")
    public ResponseEntity<Response<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
        User convertido = modelMapperService.convert(userRequest, User.class);
        return responseService.create(modelMapperService.convert(userService.saveUser(convertido), UserResponse.class));
    }
    
    @PostMapping("/user/signin")
    public ResponseEntity<Response<Token>> signin(@RequestBody AccountCredentials accountCredentials) {
    	return responseService.ok(userService.signin(accountCredentials));
    }

	@PutMapping("/user/")
    public ResponseEntity<Response<UserResponse>> update(@RequestBody UserResponse userResponse) {
        return responseService.ok(modelMapperService.convert(userService.update(modelMapperService.convert(userResponse, User.class)), UserResponse.class));
    }

    @GetMapping("/users")
    public ResponseEntity<Response<List<UserRequest>>> getAll() {
        List<UserRequest> dtos = userService.getUsers().stream()
            .map(skill -> modelMapperService.convert(skill, UserRequest.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Response<UserResponse>> getById(@PathVariable String email) {
        UserResponse user = modelMapperService.convert(userService.getUser(email), UserResponse.class);
        return responseService.ok(user);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<Response<String>> delete(@PathVariable String email) {
        if(userService.deleteByEmail(email))
            return responseService.ok("Delete is sucessfull.");
        return responseService.badRequest("Delete not sucessfull.");
    }
}
