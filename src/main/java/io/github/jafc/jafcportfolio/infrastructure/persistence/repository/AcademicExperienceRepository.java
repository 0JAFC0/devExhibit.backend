package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicExperienceRepository extends JpaRepository<AcademicExperience, Long>{
    
	@Query(value = "SELECT * FROM experience e WHERE (e.type = 'AcademicExperience') AND (e.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	Optional<List<AcademicExperience>> findByEmail(String email);

	@Query(value = "SELECT * FROM experience e WHERE (e.type = 'AcademicExperience') AND (e.user_id = :userId)", nativeQuery = true)
	Optional<List<AcademicExperience>> findAcademicExperiencesByUserId(Long userId);
}
