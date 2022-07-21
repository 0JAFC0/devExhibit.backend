package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.UserResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<UserResponse>> save(@RequestBody UserResponse userResponse) {
        User convertido = modelMapperService.convert(userResponse, User.class);
        return responseService.create(modelMapperService.convert(userService.save(convertido), UserResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<UserResponse>> update(@RequestBody UserResponse userResponse) {
        return responseService.ok(modelMapperService.convert(userService.update(modelMapperService.convert(userResponse, User.class)), UserResponse.class));
    }

    @GetMapping
    public ResponseEntity<Response<List<User>>> getAll() {
        
        return responseService.create(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<User>> getById(@PathVariable Long id) {
        return responseService.create(userService.findById(id));
    }
}
