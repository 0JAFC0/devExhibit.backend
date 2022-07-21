package io.github.jafc.jafcportfolio.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.Experience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ExperienceRepository;

@Service
public class ExperienceService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ExperienceRepository experienceRepository;

    public Experience saveExperienceUser(Experience experience) {
        User user = userService.findById(experience.getUser().getId());
        experience.setUser(user);
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Experience experience) {
        Optional<Experience> temp = experienceRepository.findById(experience.getId());
        if(!(temp.isPresent())) {
            throw new NotFoundException("Not found experience with id".concat(experience.getId().toString()));
        }
        return experienceRepository.save(experience);
    }

    public void removeExperienceUser(Experience experience) {
        User user = userService.findById(experience.getUser().getId());
        if(!(experience.getUser().getId().equals(user.getId()))) {
            throw new NotFoundException("Not found experience in user with id " + experience.getUser().getId());
        }
        experienceRepository.deleteById(experience.getId());
    }

    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    public List<Experience> getAll() {
        return experienceRepository.findAll();
    }

}
