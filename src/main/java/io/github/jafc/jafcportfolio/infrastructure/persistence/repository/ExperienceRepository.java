package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jafc.jafcportfolio.domain.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    
}
