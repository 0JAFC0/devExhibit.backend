package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ProfessionalExperienceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessionalExperienceService {

    private final UserService userService;

    private final ProfessionalExperienceRepository professionalExperienceRepository;

    public ProfessionalExperience saveProfessional(ProfessionalExperience experience) {
        User user = userService.getById(experience.getUser().getId());
        experience.setUser(user);
        return professionalExperienceRepository.save(experience);
    }

    public ProfessionalExperience updateProfessional(ProfessionalExperience experience) {
        Optional<ProfessionalExperience> temp = professionalExperienceRepository.findById(experience.getId());
        if(temp.isEmpty()) {
            throw new NotFoundException("Not found experience with id".concat(experience.getId().toString()));
        }
        return professionalExperienceRepository.save(experience);
    }

    public void deleteProfessional(ProfessionalExperience experience) {
        professionalExperienceRepository.deleteById(experience.getId());
    }
    
    public List<ProfessionalExperience> getByEmail(String email) {
        return professionalExperienceRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found user with email".concat(email)));
    }
}
