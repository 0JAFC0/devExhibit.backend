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

import io.github.jafc.jafcportfolio.application.services.ProfessionalExperienceService;
import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.ProfessionalExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/professional")
public class ProfessionalExperienceController {
    
    @Autowired
    private ProfessionalExperienceService experienceService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ProfessionalExperienceResponse>> saveExperienceUser(@RequestBody ProfessionalExperienceResponse experience) {
        return responseService.create(modelMapperService.convert(experienceService.saveProfessional(modelMapperService.convert(experience, ProfessionalExperience.class)), ProfessionalExperienceResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<ProfessionalExperienceResponse>> updateExperienceUser(@RequestBody ProfessionalExperienceResponse experience) {
        return responseService.create(modelMapperService.convert(experienceService.updateProfessional(modelMapperService.convert(experience, ProfessionalExperience.class)), ProfessionalExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> removeExperienceUser(@RequestBody ProfessionalExperienceResponse experience) {
        experienceService.deleteProfessional(modelMapperService.convert(experience, ProfessionalExperience.class));
        return responseService.create("delete Successful");
    }

    @GetMapping
    public ResponseEntity<Response<List<ProfessionalExperienceResponse>>> getAll() {
        List<ProfessionalExperienceResponse> dtos = experienceService.getAll().stream()
            .map(experience -> modelMapperService.convert(experience, ProfessionalExperienceResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
}
