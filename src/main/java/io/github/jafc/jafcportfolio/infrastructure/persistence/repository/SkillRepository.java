package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    @Query(nativeQuery = true)
    public Skill findByName(String name);
}
