package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jafc.jafcportfolio.domain.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    @Query(value = "SELECT * FROM Project p where p.user_id = :userId",nativeQuery = true)
    public Optional<List<Project>> findByUserId(Long userId);
    
    @Query(value = "SELECT * FROM project p WHERE (p.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	public Optional<List<Project>> findByEmail(@Param("email") String email);
}
