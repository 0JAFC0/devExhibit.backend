package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import io.github.jafc.jafcportfolio.domain.model.ProfessionalExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionalExperienceRepository extends JpaRepository<ProfessionalExperience, Long> {

	Optional<List<ProfessionalExperience>> findByUserEmail(String email);

	Optional<List<ProfessionalExperience>> findByUserId(Long id);

	Optional<ProfessionalExperience> findByNameAndUserEmail(String name, String email);
}
