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
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.SkillService;
import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.SkillRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.SkillResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/skill")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://0jafc0.github.io/"})
public class SkillController {

    @Autowired
    private SkillService skillService;
    
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;
    
    @PostMapping("/save")
    public ResponseEntity<Response<SkillResponse>> saveSkill(@RequestBody SkillRequest skill) {
        return responseService.create(modelMapperService.convert(skillService.saveSkillUser(modelMapperService.convert(skill, Skill.class)),SkillResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteSkill(@RequestBody SkillRequest skill) {
        skillService.removeSkillUser(modelMapperService.convert(skill, Skill.class));
        return responseService.ok("delete Successful");
    }

    @GetMapping("/skills")
    public ResponseEntity<Response<List<SkillResponse>>> getAll() {
        return responseService.ok(modelMapperService.convertList(skillService.getAll(), SkillResponse.class));
    }

    @GetMapping("/getSkillsByUserId/{userId}")
    public ResponseEntity<Response<List<SkillResponse>>> getByUserId(@PathVariable("userId") Long userId) {
        return responseService.ok(modelMapperService.convertList(skillService.getByUserId(userId), SkillResponse.class));
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Response<List<SkillResponse>>> getByEmail(@PathVariable("email") String email) {
        return responseService.ok(modelMapperService.convertList(skillService.getByEmail(email), SkillResponse.class));
    }
    
    @PutMapping
    public ResponseEntity<Response<SkillResponse>> update(@RequestBody SkillRequest skill) {
        return responseService.ok(
            modelMapperService.convert(skillService.updateSkill(
                modelMapperService.convert(skill, Skill.class)), SkillResponse.class));
    }
}
