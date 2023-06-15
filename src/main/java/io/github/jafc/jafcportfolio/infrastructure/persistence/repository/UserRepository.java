package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import io.github.jafc.jafcportfolio.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query("DELETE FROM User u WHERE u.email in :email")
	void deleteByEmail(@Param("email") String email);
}
