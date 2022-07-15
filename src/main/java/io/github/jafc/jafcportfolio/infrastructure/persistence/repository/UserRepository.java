package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jafc.jafcportfolio.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
