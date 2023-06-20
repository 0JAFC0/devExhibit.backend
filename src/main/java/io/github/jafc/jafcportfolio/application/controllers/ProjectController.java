package io.github.jafc.jafcportfolio.application.controllers;

import io.github.jafc.jafcportfolio.application.services.ProjectService;
import io.github.jafc.jafcportfolio.domain.model.Project;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.ProjectRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.ProjectResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.PROJECT_URI;

@RestController
@RequestMapping(PROJECT_URI)
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/","https://0jafc0.github.io/"})
public class ProjectController {

    private final ProjectService projectService;

    private final ModelMapperService modelMapperService;

    private final ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ProjectResponse>> save(@RequestBody ProjectRequest request,
                                                                              Principal principal) {

    	Project converter = projectService.save(modelMapperService.convert(request, Project.class),
                                                           principal.getName());

        return responseService.create(modelMapperService.convert(converter, ProjectResponse.class));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Response<ProjectResponse>> update(@PathVariable String name,
                                                                   @RequestBody ProjectRequest request,
                                                                                Principal principal) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        Project project = projectService.update(
                name,
                modelMapperService.convert(request, Project.class),
                principal.getName());
        return responseService.ok(modelMapperService.convert(project, ProjectResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> delete(@RequestParam String name, Principal principal) {
        projectService.remove(name, principal.getName());
        return responseService.ok("Delete Successful.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response<List<ProjectResponse>>> getByUserId(@PathVariable Long userId) {
        return responseService.ok(modelMapperService.convertList(projectService.getById(userId),
                ProjectResponse.class));
    }
}
