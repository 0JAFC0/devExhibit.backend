package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.request.UserRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import io.github.jafc.jafcportfolio.presentation.dto.response.UserResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.annotations.Api;

@Api(value = "End Point do usuario")
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://0jafc0.github.io/"})
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;
    
    @PostMapping("/signup")
    public ResponseEntity<Response<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
        User convertido = modelMapperService.convert(userRequest, User.class);
        return responseService.create(modelMapperService.convert(userService.saveUser(convertido), UserResponse.class));
    }
    
    @PostMapping("/signin")
    public ResponseEntity<Response<Token>> signin(@RequestBody AccountCredentials accountCredentials) {
    	return responseService.ok(userService.signin(accountCredentials));
    }

	@PutMapping
    public ResponseEntity<Response<UserResponse>> update(@RequestBody UserRequest userRequest) {
        return responseService.ok(modelMapperService.convert(userService.update(modelMapperService.convert(userRequest, User.class)), UserResponse.class));
    }

    @GetMapping("/users")
    public ResponseEntity<Response<List<UserResponse>>> getAll() {
        return responseService.ok(modelMapperService.convertList(userService.getUsers(), UserResponse.class));
    }

    @GetMapping("/userByIdExist/{id}")
    public ResponseEntity<Response<Boolean>> userByIdExist(@PathVariable Long id) {
        return responseService.ok(userService.userByIdExist(id));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Response<UserResponse>> getById(@PathVariable Long id) {
        return responseService.ok(modelMapperService.convert(userService.findById(id), UserResponse.class));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Response<UserResponse>> getByEmail(@PathVariable String email) {
        return responseService.ok(modelMapperService.convert(userService.getUserByEmail(email), 
                                                            UserResponse.class));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Response<String>> delete(@PathVariable String email) {
        if(userService.deleteByEmail(email))
            return responseService.ok("Delete is sucessfull.");
        return responseService.badRequest("Delete not sucessfull.");
    }
}
