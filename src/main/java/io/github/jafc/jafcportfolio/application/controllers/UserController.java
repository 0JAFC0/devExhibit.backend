package io.github.jafc.jafcportfolio.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.SkillResponse;
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

    @GetMapping
    public ResponseEntity<Response<UserResponse>> getAll() {
        return responseService.create(modelMapperService.convert(userService.findAll(), UserResponse.class));
    }

    @PostMapping("/add-skill")
    public ResponseEntity<Response<UserResponse>> addSkillInUser(@RequestBody SkillResponse skillResponse) {
        UserResponse user = modelMapperService.convert(userService.addSkillInUser(skillResponse.getUser().getId(), 
            modelMapperService.convert(skillResponse, Skill.class)), UserResponse.class);

        return responseService.create(user);
    }

    @DeleteMapping("/remove-skill")
    public ResponseEntity<Response<UserResponse>> removeSkillInUser(@RequestBody SkillResponse skillResponse) {
        UserResponse user = modelMapperService.convert(userService.removeSkillInUser(skillResponse.getUser().getId(), 
            modelMapperService.convert(skillResponse, Skill.class)), UserResponse.class);

        return responseService.ok(user);
    }
}
