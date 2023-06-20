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

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
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
    public ResponseEntity<Response<ProfessionalExperienceResponse>> save(@RequestBody ProfessionalExperienceRequest request, Principal principal) {
        ProfessionalExperience experience = experienceService.save(modelMapperService.convert(request, ProfessionalExperience.class), principal.getName());
        return responseService.create(modelMapperService.convert(experience, ProfessionalExperienceResponse.class));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Response<ProfessionalExperienceResponse>> update(@PathVariable String name,
                                                                                         @RequestBody ProfessionalExperienceRequest experience, Principal principal) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        ProfessionalExperience professionalExperience = experienceService.update(
                name,
                modelMapperService.convert(experience, ProfessionalExperience.class),
                principal.getName());

        return responseService.ok(modelMapperService.convert(professionalExperience, ProfessionalExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> remove(@RequestParam String name, Principal principal) {
        experienceService.delete(name, principal.getName());
        return responseService.ok("delete Successful");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response<List<ProfessionalExperienceResponse>>> getByUserId(@PathVariable Long userId) {
        return responseService.ok(modelMapperService.convertList(experienceService.getByUserId(userId),
                ProfessionalExperienceResponse.class));
    }
}
