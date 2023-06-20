package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.Project;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ProjectRepository;
import io.github.jafc.jafcportfolio.infrastructure.utils.BeansUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private final UserService userService;

    private final ProjectRepository projectRepository;

    public Project save(Project project, String email) {
        User user = userService.getUserByEmail(email);
        project.setUser(user);
        return projectRepository.save(project);
    }

    public Project update(String name, Project request, String email) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Project project = getProjectByNameAndUserEmail(name, email);

        String[] ignoreProperties = {"id", "name", "user"};

        BeansUtils.copyPropertiesIgnoreNull(project, request, ignoreProperties);

        return projectRepository.save(project);
    }

    public void remove(String nameProject, String email) {
        Project project = getProjectByNameAndUserEmail(nameProject, email);
        projectRepository.deleteById(project.getId());
    }

    private Project getProjectByNameAndUserEmail(String name, String email) {
        return this.projectRepository.findByNameAndUserEmail(name, email)
                .orElseThrow(() -> new NotFoundException("The project with name " + name + " not found in user"));
    }
    
    public List<Project> getByEmail(String email) {
        return projectRepository.findByUserEmail(email)
            .orElseThrow(() -> new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }

    public List<Project> getById(Long id) {
        return projectRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException("The user with id ".concat(id.toString()).concat(" not found!")));
    }
}
