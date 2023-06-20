package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.AcademicExperienceRepository;
import io.github.jafc.jafcportfolio.infrastructure.utils.BeansUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
public class AcademicExperienceService {

    private final UserService userService;

    private final AcademicExperienceRepository academicExperienceRepository;

    public AcademicExperience save(AcademicExperience academicExperience, String email) {
        User user = userService.getUserByEmail(email);
        academicExperience.setUser(user);
        return academicExperienceRepository.save(academicExperience);
    }

    public AcademicExperience update(String name, AcademicExperience request, String email) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        AcademicExperience academicExperience = getByNameAndUserEmail(name, email);

        String[] ignoreProperties = {"id", "name", "user"};

        BeansUtils.copyPropertiesIgnoreNull(academicExperience, request, ignoreProperties);

        return academicExperienceRepository.save(academicExperience);
    }

    public void delete(String name, String email) {
        AcademicExperience academicExperience = getByNameAndUserEmail(name, email);
        academicExperienceRepository.deleteById(academicExperience.getId());
    }
    
    public List<AcademicExperience> getByUserId(Long userId) {
    	return academicExperienceRepository.findAcademicExperienceByUserId(userId)
                .orElseThrow(() -> new NotFoundException("The user with id ".concat(userId.toString()).concat(" not found!")));
    }

    private AcademicExperience getByNameAndUserEmail(String name, String email) {
        return academicExperienceRepository.findByNameAndUserEmail(name, email).orElseThrow(() -> new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }
}
