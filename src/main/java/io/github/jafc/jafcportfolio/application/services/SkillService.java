package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.ExistException;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    private final UserService userService;

    public Skill saveSkillUser(Skill skill, String email) {
        User user = userService.getUserByEmail(email);

        if(skillRepository.existsByNameAndUserId(skill.getName(), user.getId())) {
            throw new ExistException("Já existe uma skill com esse nome");
        }

        skill.setUser(user);
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill, String email) {
        Optional<Skill> temp = skillRepository.findById(skill.getId());
        if(temp.isEmpty()) {
            throw new NotFoundException("Not found skill with id".concat(skill.getId().toString()));
        }
        User user = userService.getUserByEmail(email);
        skill.setUser(user);

        return skillRepository.save(skill);
    }

    public void removeSkillUser(Skill skill, String email) {
        User user = userService.getUserByEmail(email);
        skill.setUser(user);
        skillRepository.deleteById(skill.getId());
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
