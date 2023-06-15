package io.github.jafc.jafcportfolio.application.controllers;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.request.UserRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import io.github.jafc.jafcportfolio.presentation.dto.response.UserResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.USER_URI;

@Tag(name = "user-controller", description = "End Point do usuario")
@RestController
@RequestMapping(USER_URI)
@CrossOrigin(origins = {"http://localhost:4200/","https://0jafc0.github.io/"})
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final ModelMapperService modelMapperService;

    private final ResponseService responseService;

    @PostMapping("/signup")
    @Operation(summary = "Salva um usuario")
    public ResponseEntity<Response<UserResponse>> saveUser(@ParameterObject @RequestBody UserRequest userRequest) {
        User convertido = modelMapperService.convert(userRequest, User.class);
        return responseService.create(modelMapperService.convert(userService.signup(convertido), UserResponse.class));
    }

    @PostMapping("/signin")
    @Operation(summary = "Loga um usuario")
    public ResponseEntity<Response<Token>> signin(@ParameterObject @RequestBody AccountCredentials accountCredentials) {
        return responseService.ok(userService.signin(accountCredentials));
    }

    @GetMapping("/google/callback")
    @Operation(summary = "Callback para Loga um usuario utilizando o google")
    public ResponseEntity<Response<Token>> googleSignin(@ParameterObject @RequestParam("code") String code) {
        return responseService.ok(userService.getAccessTokenWithGoogle(code));
    }

    @GetMapping("/google/redireciona/auth")
    @Operation(summary = "Redireciona para o google auth")
    public void redirecionarToGoogleAuth(HttpServletResponse response) throws IOException {
        String url = userService.redirecionarToGoogleAuth();
        response.sendRedirect(url);
    }

    @PutMapping
    public ResponseEntity<Response<UserResponse>> update(@ParameterObject @RequestBody UserRequest userRequest, Principal principal) {
        return responseService.ok(modelMapperService.convert(userService.update(modelMapperService.convert(userRequest, User.class), principal.getName()), UserResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> delete(Principal principal) {
        if(userService.deleteByEmail(principal.getName()))
            return responseService.ok("Delete is sucessfull.");
        return responseService.badRequest("Delete not sucessfull.");
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Response<Boolean>> userByIdExist(@ParameterObject @PathVariable Long id) {
        return responseService.ok(userService.userByIdExist(id));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Response<UserResponse>> getById(@ParameterObject @PathVariable Long id) {
        return responseService.ok(modelMapperService.convert(userService.getById(id), UserResponse.class));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Response<UserResponse>> getByEmail(@ParameterObject @PathVariable String email) {
        return responseService.ok(modelMapperService.convert(userService.getUserByEmail(email),
                UserResponse.class));
    }

    @GetMapping("/count")
    public ResponseEntity<Response<Long>> getCountUsers() {
        return responseService.ok(this.userService.countUsers());
    }
}


