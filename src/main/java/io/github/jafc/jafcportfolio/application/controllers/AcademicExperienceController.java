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

import io.github.jafc.jafcportfolio.application.services.AcademicExperienceService;
import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.AcademicExperienceRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.AcademicExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/academic")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://jafc-backend.herokuapp.com/"})
public class AcademicExperienceController {
    
    @Autowired
    private AcademicExperienceService academicExperienceService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<AcademicExperienceResponse>> saveExperienceUser(@RequestBody AcademicExperienceRequest experience) {
        return responseService.create(modelMapperService.convert(academicExperienceService.saveAcademic(modelMapperService.convert(experience, AcademicExperience.class)), AcademicExperienceResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<AcademicExperienceResponse>> updateExperienceUser(@RequestBody AcademicExperienceRequest experience) {
        return responseService.ok(modelMapperService.convert(academicExperienceService.updateAcademic(modelMapperService.convert(experience, AcademicExperience.class)), AcademicExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> removeExperienceUser(@RequestBody AcademicExperienceRequest experience) {
        academicExperienceService.deleteAcademic(modelMapperService.convert(experience, AcademicExperience.class));
        return responseService.ok("delete Successful");
    }

    @GetMapping("/academics")
    public ResponseEntity<Response<List<AcademicExperienceResponse>>> getAll() {
        return responseService.ok(modelMapperService.convertList(academicExperienceService.getAll(), AcademicExperienceResponse.class));
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Response<List<AcademicExperienceResponse>>> getByEmail(@PathVariable String email) {
        return responseService.ok(modelMapperService.convertList(academicExperienceService.getByEmail(email), AcademicExperienceResponse.class));
    }
}
