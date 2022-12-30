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

import io.github.jafc.jafcportfolio.application.services.ProfessionalExperienceService;
import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.ProfessionalExperienceRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.ProfessionalExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/professional")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://0jafc0.github.io/"})
public class ProfessionalExperienceController {
    
    @Autowired
    private ProfessionalExperienceService experienceService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ProfessionalExperienceResponse>> saveExperienceUser(@RequestBody ProfessionalExperienceRequest experience) {
        return responseService.create(modelMapperService.convert(experienceService.saveProfessional(modelMapperService.convert(experience, ProfessionalExperience.class)), ProfessionalExperienceResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<ProfessionalExperienceResponse>> updateExperienceUser(@RequestBody ProfessionalExperienceRequest experience) {
        return responseService.ok(modelMapperService.convert(experienceService.updateProfessional(modelMapperService.convert(experience, ProfessionalExperience.class)), ProfessionalExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> removeExperienceUser(@RequestBody ProfessionalExperienceRequest experience) {
        experienceService.deleteProfessional(modelMapperService.convert(experience, ProfessionalExperience.class));
        return responseService.ok("delete Successful");
    }

    @GetMapping("/professionals")
    public ResponseEntity<Response<List<ProfessionalExperienceResponse>>> getAll() {
        return responseService.ok(modelMapperService.convertList(experienceService.getAll(), ProfessionalExperienceResponse.class));
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ProfessionalExperienceResponse>>> getByEmail(@PathVariable String email) {
        return responseService.ok(modelMapperService.convertList(experienceService.getByEmail(email), ProfessionalExperienceResponse.class));
    }
}
