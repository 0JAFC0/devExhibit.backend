package io.github.jafc.jafcportfolio.application.controllers;

import io.github.jafc.jafcportfolio.application.services.SkillService;
import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.SkillRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.SkillResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.SKILL_URI;

@Tag(name = "skill-controller", description = "End Point de skills")
@RestController
@RequestMapping(SKILL_URI)
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/","https://0jafc0.github.io/"})
public class SkillController {

    private final SkillService skillService;

    private final ModelMapperService modelMapperService;

    private final ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<SkillResponse>> save(@RequestBody SkillRequest request, Principal principal) {
        Skill skillResponse = skillService.save(modelMapperService.convert(request, Skill.class),
                                                         principal.getName());

        return responseService.create(modelMapperService.convert(skillResponse,SkillResponse.class));
    }

    @PutMapping("{name}")
    public ResponseEntity<Response<SkillResponse>> update(@PathVariable String name,
                                                          @RequestBody SkillRequest request,
                                                          Principal principal) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Skill skillResponse = skillService.update(
                name,
                modelMapperService.convert(request, Skill.class),
                principal.getName());

        return responseService.ok(modelMapperService.convert(skillResponse, SkillResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> delete(@RequestParam String name, Principal principal) {
        skillService.remove(name, principal.getName());
        return responseService.ok("delete Successful");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response<List<SkillResponse>>> getByUserId(@PathVariable Long userId) {
        List<SkillResponse> skillResponses = modelMapperService.convertList(skillService.getByUserId(userId),
                                                                            SkillResponse.class);

        return responseService.ok(skillResponses);
    }
}
