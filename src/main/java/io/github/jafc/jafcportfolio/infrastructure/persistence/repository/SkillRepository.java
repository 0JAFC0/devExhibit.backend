package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import io.github.jafc.jafcportfolio.domain.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<List<Skill>> findSkillsByUserId(@Param("userId") Long userId);
    
    @Query(value = "SELECT * FROM Skill s WHERE (s.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	Optional<List<Skill>> findByEmail(@Param("email") String email);

    boolean existsByNameAndUserId(String name, Long userId);
    
}
