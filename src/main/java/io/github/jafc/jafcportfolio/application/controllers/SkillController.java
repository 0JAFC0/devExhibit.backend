package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.SkillService;
import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.SkillResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;
    
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;
    
    @PostMapping("/save")
    public ResponseEntity<Response<SkillResponse>> saveSkill(@RequestBody SkillResponse skill) {
        return responseService.create(modelMapperService.convert(skillService.saveSkillUser(modelMapperService.convert(skill, Skill.class)),SkillResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteSkill(@RequestBody SkillResponse skill) {
        skillService.removeSkillUser(modelMapperService.convert(skill, Skill.class));
        return responseService.create("delete Successful");
    }

    @GetMapping
    public ResponseEntity<Response<List<SkillResponse>>> getAll() {
        List<SkillResponse> dtos = skillService.getAll().stream()
            .map(skill -> modelMapperService.convert(skill, SkillResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
    
    @PutMapping
    public ResponseEntity<Response<SkillResponse>> update(@RequestBody SkillResponse skill) {
        return responseService.ok(
            modelMapperService.convert(skillService.updateSkill(
                modelMapperService.convert(skill, Skill.class)), SkillResponse.class));
    }
}
