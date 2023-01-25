package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jafc.jafcportfolio.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);

	boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query("DELETE FROM User u WHERE u.email in :email")
	void deleteByEmail(@Param("email") String email);
}
