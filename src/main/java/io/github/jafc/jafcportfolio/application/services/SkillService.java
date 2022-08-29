package io.github.jafc.jafcportfolio.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.Skill;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserService userService;

    public Skill saveSkillUser(Skill skill) {
        User user = userService.findById(skill.getUser().getId());
        skill.setUser(user);
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {
        Optional<Skill> temp = skillRepository.findById(skill.getId());
        if(!temp.isPresent()) {
            throw new NotFoundException("Not found skill with id".concat(skill.getId().toString()));
        }
        return skillRepository.save(skill);
    }

    public void removeSkillUser(Skill skill) {
        User user = userService.findById(skill.getUser().getId());
        if(!(skill.getUser().getId().equals(user.getId()))) {
            throw new NotFoundException("Not found skill in user with id " + skill.getUser().getId());
        }
        skillRepository.deleteById(skill.getId());
    }

    public List<Skill> getSkillsByUserID(Long userId) {
        List<Skill> skills = skillRepository.findSkillsByUserID(userId);
        if(skills.isEmpty()) {
            throw new NotFoundException("Not found skills in user with id ".concat(userId.toString()));
        }
        return skills;
    }
    
    public List<Skill> getByEmail(String email) {
        return skillRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }

    public List<Skill> getAll() {
        return skillRepository.findAll();
    }
}
