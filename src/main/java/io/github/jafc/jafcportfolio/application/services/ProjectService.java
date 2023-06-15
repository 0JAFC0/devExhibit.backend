package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.Project;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final UserService userService;

    private final ProjectRepository projectRepository;

    public Project saveProjectUser(Project project, String email) {
        User user = userService.getUserByEmail(email);
        project.setUser(user);
        return projectRepository.save(project);
    }

    public Project updateProjectUser(Project project, String email) {
        Optional<Project> temp = projectRepository.findById(project.getId());
        if(temp.isEmpty()) {
            throw new NotFoundException("Not found project with id".concat(project.getId().toString()));
        }
        return projectRepository.save(project);
    }

    public void removeProjectUser(Project project, String email) {
        User user = userService.getUserByEmail(email);
        project.setUser(user);
        projectRepository.delete(project);
    }
    
    public List<Project> getByEmail(String email) {
        return projectRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }
}
