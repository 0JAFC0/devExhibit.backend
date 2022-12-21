package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;

@Repository
public interface AcademicExperienceRepository extends JpaRepository<AcademicExperience, Long>{
    
	@Query(value = "SELECT * FROM experience e WHERE (e.experience_type = 'AcademicExperience') AND (e.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	public Optional<List<AcademicExperience>> findByEmail(@Param("email") String email);
}
