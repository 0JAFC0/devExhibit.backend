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

import io.github.jafc.jafcportfolio.application.services.AcademicExperienceService;
import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.AcademicExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/academic")
public class AcademicExperienceController {
    
    @Autowired
    private AcademicExperienceService academicExperienceService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<AcademicExperienceResponse>> saveExperienceUser(@RequestBody AcademicExperienceResponse experience) {
        return responseService.create(modelMapperService.convert(academicExperienceService.saveAcademic(modelMapperService.convert(experience, AcademicExperience.class)), AcademicExperienceResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<AcademicExperienceResponse>> updateExperienceUser(@RequestBody AcademicExperienceResponse experience) {
        return responseService.create(modelMapperService.convert(academicExperienceService.updateAcademic(modelMapperService.convert(experience, AcademicExperience.class)), AcademicExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> removeExperienceUser(@RequestBody AcademicExperienceResponse experience) {
        academicExperienceService.deleteAcademic(modelMapperService.convert(experience, AcademicExperience.class));
        return responseService.create("delete Successful");
    }

    @GetMapping
    public ResponseEntity<Response<List<AcademicExperienceResponse>>> getAll() {
        List<AcademicExperienceResponse> dtos = academicExperienceService.getAll().stream()
            .map(experience -> modelMapperService.convert(experience, AcademicExperienceResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
}
