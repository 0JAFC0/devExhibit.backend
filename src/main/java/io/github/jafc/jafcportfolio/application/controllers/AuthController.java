package io.github.jafc.jafcportfolio.application.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.security.AccountCredentials;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private UserService userService;
	
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;
	
	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody AccountCredentials accountCredentials) {
		if (checkIfParamsIsNotNull(accountCredentials)) {
			return responseService.forbidden("Invalid client request!");
		}
		var token = userService.signin(accountCredentials);
		if (Objects.isNull(token)) 
			return responseService.forbidden("Signin Unsuccessful");
		return responseService.ok(token);
	}

	private boolean checkIfParamsIsNotNull(AccountCredentials data) {
		return Objects.isNull(data) 
				|| Objects.isNull(data.getEmail()) 
				|| Objects.isNull(data.getPassword())
				|| data.getEmail().isBlank()
				|| data.getPassword().isBlank();
	}
}
