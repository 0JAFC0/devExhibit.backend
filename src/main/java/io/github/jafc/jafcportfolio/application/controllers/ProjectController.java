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
    public ResponseEntity<Response<ProjectResponse>> saveProject(@RequestBody ProjectRequest project, Principal principal) {
    	Project converter = projectService.saveProjectUser(modelMapperService.convert(project, Project.class), principal.getName());
        return responseService.create(modelMapperService.convert(converter, ProjectResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<ProjectResponse>> updateProject(@RequestBody ProjectRequest project, Principal principal) {
        return responseService.ok(modelMapperService.convert(projectService.updateProjectUser(modelMapperService.convert(project, Project.class), principal.getName()), ProjectResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteProject(@RequestBody ProjectRequest project, Principal principal) {
        projectService.removeProjectUser(modelMapperService.convert(project, Project.class), principal.getName());
        return responseService.ok("Delete Successful.");
    }

    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ProjectResponse>>> getByEmail(@PathVariable("email") String email) {
        return responseService.ok(modelMapperService.convertList(projectService.getByEmail(email), ProjectResponse.class));
    }
}
