package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.AcademicExperienceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AcademicExperienceService {

    private final UserService userService;

    private final AcademicExperienceRepository academicExperienceRepository;

    public AcademicExperience saveAcademic(AcademicExperience academicExperience) {
        User user = userService.getById(academicExperience.getUser().getId());
        academicExperience.setUser(user);
        return academicExperienceRepository.save(academicExperience);
    }

    public AcademicExperience updateAcademic(AcademicExperience academicExperience) {
        Optional<AcademicExperience> temp = academicExperienceRepository.findById(academicExperience.getId());
        if(temp.isEmpty()) {
            throw new NotFoundException("Not found academic experience with id ".concat(academicExperience.getId().toString()));
        }
        return academicExperienceRepository.save(academicExperience);
    }

    public void deleteAcademic(AcademicExperience academicExperience) {
        academicExperienceRepository.deleteById(academicExperience.getId());
    }
    
    public List<AcademicExperience> getByEmail(String email) {
    	return academicExperienceRepository.findByEmail(email).orElseThrow(()->new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }
}
