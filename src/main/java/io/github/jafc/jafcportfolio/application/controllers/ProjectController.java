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

import io.github.jafc.jafcportfolio.application.services.ProjectService;
import io.github.jafc.jafcportfolio.domain.model.Project;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.ProjectResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ProjectResponse>> saveProject(@RequestBody ProjectResponse project) {
        return responseService.create(modelMapperService.convert(projectService.saveProjectUser(modelMapperService.convert(project, Project.class)), ProjectResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<ProjectResponse>> updateProject(@RequestBody ProjectResponse project) {
        return responseService.ok(modelMapperService.convert(projectService.updateProjectUser(modelMapperService.convert(project, Project.class)), ProjectResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteProject(@RequestBody ProjectResponse project) {
        projectService.removeProjectUser(modelMapperService.convert(project, Project.class));
        return responseService.ok("Delete Successful.");
    }

    @GetMapping
    public ResponseEntity<Response<List<ProjectResponse>>> getAll() {
        List<ProjectResponse> dtos = projectService.getAll().stream()
            .map(project -> modelMapperService.convert(project, ProjectResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
}
