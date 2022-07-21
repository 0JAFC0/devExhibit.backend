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

import io.github.jafc.jafcportfolio.application.services.ExperienceService;
import io.github.jafc.jafcportfolio.domain.model.Experience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.ExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {
    
    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ExperienceResponse>> saveExperienceUser(@RequestBody ExperienceResponse experience) {
        return responseService.create(modelMapperService.convert(experienceService.saveExperienceUser(modelMapperService.convert(experience, Experience.class)), ExperienceResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<ExperienceResponse>> updateExperienceUser(@RequestBody ExperienceResponse experience) {
        return responseService.create(modelMapperService.convert(experienceService.updateExperience(modelMapperService.convert(experience, Experience.class)), ExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> removeExperienceUser(@RequestBody ExperienceResponse experience) {
        experienceService.removeExperienceUser(modelMapperService.convert(experience, Experience.class));
        return responseService.create("delete Successful");
    }

    @GetMapping
    public ResponseEntity<Response<List<ExperienceResponse>>> getAll() {
        List<ExperienceResponse> dtos = experienceService.getAll().stream()
            .map(experience -> modelMapperService.convert(experience, ExperienceResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
}
