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

import io.github.jafc.jafcportfolio.application.services.ProjectService;
import io.github.jafc.jafcportfolio.domain.model.Project;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.ProjectRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.ProjectResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://0jafc0.github.io/"})
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ProjectResponse>> saveProject(@RequestBody ProjectRequest project) {
    	Project converter = projectService.saveProjectUser(modelMapperService.convert(project, Project.class));
        return responseService.create(modelMapperService.convert(converter, ProjectResponse.class));
    }

    @PutMapping
    public ResponseEntity<Response<ProjectResponse>> updateProject(@RequestBody ProjectRequest project) {
        return responseService.ok(modelMapperService.convert(projectService.updateProjectUser(modelMapperService.convert(project, Project.class)), ProjectResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteProject(@RequestBody ProjectRequest project) {
        projectService.removeProjectUser(modelMapperService.convert(project, Project.class));
        return responseService.ok("Delete Successful.");
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ProjectResponse>>> getByEmail(@PathVariable("email") String email) {
        return responseService.ok(modelMapperService.convertList(projectService.getByEmail(email), ProjectResponse.class));
    }

    @GetMapping("/projects")
    public ResponseEntity<Response<List<ProjectResponse>>> getAll() {
        return responseService.ok(modelMapperService.convertList(projectService.getAll(), ProjectResponse.class));
    }
}
