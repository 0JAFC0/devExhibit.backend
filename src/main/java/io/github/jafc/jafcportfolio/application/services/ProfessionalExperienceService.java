package io.github.jafc.jafcportfolio.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ProfessionalExperienceRepository;

@Service
public class ProfessionalExperienceService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProfessionalExperienceRepository professionalExperienceRepository;

    public ProfessionalExperience saveProfessional(ProfessionalExperience experience) {
        User user = userService.findById(experience.getUser().getId());
        experience.setUser(user);
        return professionalExperienceRepository.save(experience);
    }

    public ProfessionalExperience updateProfessional(ProfessionalExperience experience) {
        Optional<ProfessionalExperience> temp = professionalExperienceRepository.findById(experience.getId());
        if(!(temp.isPresent())) {
            throw new NotFoundException("Not found experience with id".concat(experience.getId().toString()));
        }
        return professionalExperienceRepository.save(experience);
    }

    public void deleteProfessional(ProfessionalExperience experience) {
        professionalExperienceRepository.deleteById(experience.getId());
    }

    public void deleteById(Long id) {
        professionalExperienceRepository.deleteById(id);
    }

    public List<ProfessionalExperience> getAll() {
        return professionalExperienceRepository.findAll();
    }

}
