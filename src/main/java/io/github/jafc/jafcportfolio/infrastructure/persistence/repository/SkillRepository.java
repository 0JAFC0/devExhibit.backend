package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    @Query(value = "SELECT * FROM Skill s where s.user_id = :userId",nativeQuery = true)
    public List<Skill> findSkillsByUserID(@Param("userId") Long userId);

    @Query(nativeQuery = true)
    public Skill findByName(String name);
}
