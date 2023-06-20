package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ProfessionalExperienceRepository;
import io.github.jafc.jafcportfolio.infrastructure.utils.BeansUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfessionalExperienceService {

    private final UserService userService;

    private final ProfessionalExperienceRepository professionalExperienceRepository;

    public ProfessionalExperience save(ProfessionalExperience experience, String email) {
        User user = userService.getUserByEmail(email);
        experience.setUser(user);
        return professionalExperienceRepository.save(experience);
    }

    public ProfessionalExperience update(String name, ProfessionalExperience request, String email) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        ProfessionalExperience professionalExperience = getByNameAndUserId(name, email);

        String[] ignoreProperties = {"id", "name", "user"};

        BeansUtils.copyPropertiesIgnoreNull(professionalExperience, request, ignoreProperties);

        return professionalExperienceRepository.save(professionalExperience);
    }

    public void delete(String name, String email) {
        ProfessionalExperience professionalExperience = getByNameAndUserId(name, email);
        professionalExperienceRepository.deleteById(professionalExperience.getId());
    }

    public List<ProfessionalExperience> getByUserId(Long id) {
        return professionalExperienceRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException("The user with id ".concat(id.toString()).concat(" not found!")));
    }

    
    public List<ProfessionalExperience> getByEmail(String email) {
        return professionalExperienceRepository.findByUserEmail(email).orElseThrow(() -> new NotFoundException("Not found user with email"+ email));
    }

    private ProfessionalExperience getByNameAndUserId(String name, String email) {
        return professionalExperienceRepository.findByNameAndUserEmail(name, email).orElseThrow(() -> new NotFoundException("Not found professional experience with name "+ name + "in user"));
    }
}
