package io.github.jafc.jafcportfolio.application.controllers;

import io.github.jafc.jafcportfolio.application.services.ProfessionalExperienceService;
import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.ProfessionalExperienceRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.ProfessionalExperienceResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.PROFESSIONAL_EXPERIENCE_URI;

@RestController
@RequestMapping(PROFESSIONAL_EXPERIENCE_URI)
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/","https://0jafc0.github.io/"})
public class ProfessionalExperienceController {

    private final ProfessionalExperienceService experienceService;

    private final ModelMapperService modelMapperService;

    private final ResponseService responseService;

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

    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ProfessionalExperienceResponse>>> getByEmail(@PathVariable String email) {
        return responseService.ok(modelMapperService.convertList(experienceService.getByEmail(email), ProfessionalExperienceResponse.class));
    }
}
