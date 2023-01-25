package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jafc.jafcportfolio.domain.model.ERole;
import io.github.jafc.jafcportfolio.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);
}
