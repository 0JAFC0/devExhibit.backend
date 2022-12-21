package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jafc.jafcportfolio.domain.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    @Query(value = "SELECT * FROM Skill s where s.user_id = :userId",nativeQuery = true)
    public List<Skill> findSkillsByUserID(@Param("userId") Long userId);
    
    @Query(value = "SELECT * FROM Skill s WHERE (s.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	public Optional<List<Skill>> findByEmail(@Param("email") String email);

    boolean existsByName(String name);
    
}
