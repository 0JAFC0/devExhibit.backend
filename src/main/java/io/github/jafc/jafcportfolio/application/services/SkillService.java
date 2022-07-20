package io.github.jafc.jafcportfolio.application.services;

import java.util.List;

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

    public Skill saveSkillUser(Long id, Skill skill) {
        User user = userService.findById(id);
        skill.setUser(user);
        return skillRepository.save(skill);
    }

    public void removeSkillUser(Long id, Skill skill) {
        User user = userService.findById(id);
        if(skill.getUser().getId().equals(user.getId())) {
            skillRepository.deleteById(skill.getId());
        }else {
            throw new NotFoundException("Not found skill in user with id " + id);
        }
    }

    public List<Skill> getAll() {
        return skillRepository.findAll();
    }
}
