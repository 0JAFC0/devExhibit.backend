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

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
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
    public ResponseEntity<Response<AcademicExperienceResponse>> save(@RequestBody AcademicExperienceRequest experience,
                                                                                                Principal principal) {

        AcademicExperience academicExperience = academicExperienceService.save(modelMapperService.convert(experience, AcademicExperience.class), principal.getName());
        return responseService.create(modelMapperService.convert(academicExperience, AcademicExperienceResponse.class));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Response<AcademicExperienceResponse>> update(@PathVariable String name,
                                                                                     @RequestBody AcademicExperienceRequest experience,
                                                                                                  Principal principal) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        AcademicExperience academic = academicExperienceService.update(name, modelMapperService.convert(experience, AcademicExperience.class), principal.getName());
        return responseService.ok(modelMapperService.convert(academic, AcademicExperienceResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> remove(@RequestParam String name,
                                                                 Principal principal) {

        academicExperienceService.delete(name, principal.getName());

        return responseService.ok("delete Successful");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response<List<AcademicExperienceResponse>>> getByUserId(@PathVariable Long userId) {
        return responseService.ok(modelMapperService.convertList(academicExperienceService.getByUserId(userId),
                                  AcademicExperienceResponse.class));
    }
}
