package io.github.jafc.jafcportfolio.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.Project;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProjectUser(Project project) {
        User user = userService.findById(project.getUser().getId());
        project.setUser(user); 
        return projectRepository.save(project);
    }

    public Project updateProjectUser(Project project) {
        Optional<Project> temp = projectRepository.findById(project.getId());
        if(!(temp.isPresent())) {
            throw new NotFoundException("Not found project with id".concat(project.getId().toString()));
        }
        return projectRepository.save(project);
    }

    public void removeProjectUser(Project project) {
        User user = userService.findById(project.getUser().getId());
        if(!(project.getUser().getId().equals(user.getId()))) {
            throw new NotFoundException("Not found project in user with id " + project.getUser().getId());
        }
        projectRepository.deleteById(project.getId());
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }
}
