package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import io.github.jafc.jafcportfolio.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<List<Project>> findByUserEmail(String email);

    Optional<Project> findByNameAndUserEmail(String name, String email);

    Optional<List<Project>> findByUserId(Long id);
}
