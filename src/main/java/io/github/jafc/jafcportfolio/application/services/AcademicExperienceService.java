package io.github.jafc.jafcportfolio.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.AcademicExperienceRepository;

@Service
public class AcademicExperienceService {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private AcademicExperienceRepository academicExperienceRepository;

    public AcademicExperience saveAcademic(AcademicExperience academicExperience) {
        User user = userService.findById(academicExperience.getUser().getId());
        academicExperience.setUser(user);
        return academicExperienceRepository.save(academicExperience);
    }

    public AcademicExperience updateAcademic(AcademicExperience academicExperience) {
        Optional<AcademicExperience> temp = academicExperienceRepository.findById(academicExperience.getId());
        if(!temp.isPresent()) {
            throw new NotFoundException("Not found academic experience with id ".concat(academicExperience.getId().toString()));
        }
        return academicExperienceRepository.save(academicExperience);
    }

    public void deleteAcademic(AcademicExperience academicExperience) {
        academicExperienceRepository.deleteById(academicExperience.getId());
    }

    public void deleteById(Long id) {
        academicExperienceRepository.deleteById(id);
    }
    
    public List<AcademicExperience> getByEmail(String email) {
    	return academicExperienceRepository.findByEmail(email).orElseThrow(()->new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }

    public List<AcademicExperience> getByUserId(Long userId) {
        return academicExperienceRepository.findAcademicExperiencesByUserId(userId).orElseThrow(() -> new NotFoundException("Not found user with email".concat(userId.toString())));
    }

    public List<AcademicExperience> getAll() {
        return academicExperienceRepository.findAll();
    }
}
