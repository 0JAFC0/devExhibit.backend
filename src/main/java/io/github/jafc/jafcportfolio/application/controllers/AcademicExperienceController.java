package io.github.jafc.jafcportfolio.application.controllers;

import io.github.jafc.jafcportfolio.application.services.AcademicExperienceService;
import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.AcademicExperienceRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.AcademicExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.ACADEMIC_EXPERIENCE_URI;

@RestController
@RequestMapping(ACADEMIC_EXPERIENCE_URI)
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/","https://0jafc0.github.io/"})
public class AcademicExperienceController {

    private final AcademicExperienceService academicExperienceService;

    private final ModelMapperService modelMapperService;

    private final ResponseService responseService;

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

    @GetMapping("/{email}")
    public ResponseEntity<Response<List<AcademicExperienceResponse>>> getByEmail(@PathVariable String email) {
        return responseService.ok(modelMapperService.convertList(academicExperienceService.getByEmail(email), AcademicExperienceResponse.class));
    }
}
