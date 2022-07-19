package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
