package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import io.github.jafc.jafcportfolio.domain.model.AcademicExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicExperienceRepository extends JpaRepository<AcademicExperience, Long>{

	Optional<AcademicExperience> findByNameAndUserEmail(String name, String email);

	Optional<List<AcademicExperience>> findAcademicExperienceByUserId(Long id);
}
