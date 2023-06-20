package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.ExistException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.SkillRepository;
import io.github.jafc.jafcportfolio.infrastructure.utils.BeansUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    private final UserService userService;

    public Skill save(Skill skill, String email) {
        User user = userService.getUserByEmail(email);

        if(skillRepository.existsByNameAndUserId(skill.getName(), user.getId())) {
            throw new ExistException("Já existe uma skill com esse nome");
        }

        skill.setUser(user);
        return skillRepository.save(skill);
    }

    public Skill update(String name, Skill request, String email) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Skill skill = getByNameAndUserEmail(name, email);

        String[] ignoreProperties = {"id", "name", "user"};

        BeansUtils.copyPropertiesIgnoreNull(skill, request, ignoreProperties);

        return skillRepository.save(skill);
    }

    public void remove(String name, String email) {
        Skill skill = getByNameAndUserEmail(name, email);
        skillRepository.deleteById(skill.getId());
    }

    private Skill getByNameAndUserEmail(String name, String email) {
        return skillRepository.findByNameAndUserEmail(name, email).orElseThrow(() -> new NotFoundException("Not found skill ".concat(name).concat("in User.")));
    }

    public List<Skill> getByUserId(Long userId) {
        return skillRepository.findSkillsByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Not found skills in user with id ".concat(userId.toString())));
    }
    
    public List<Skill> getByEmail(String email) {
        return skillRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }
}
