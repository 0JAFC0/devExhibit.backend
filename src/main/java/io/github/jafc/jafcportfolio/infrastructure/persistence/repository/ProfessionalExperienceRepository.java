package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;

public interface ProfessionalExperienceRepository extends JpaRepository<ProfessionalExperience, Long> {
	
	@Query(value = "SELECT * FROM experience e WHERE (e.experience_type = 'ProfessionalExperience') AND (e.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	public Optional<List<ProfessionalExperience>> findByEmail(@Param("email") String email);

	@Query(value = "SELECT * FROM experience e WHERE (e.experience_type = 'ProfessionalExperience') AND (e.user_id = :userId)",nativeQuery = true)
    public Optional<List<ProfessionalExperience>> findProfessionalExperiencesByUserId(@Param("userId") Long userId);
}
