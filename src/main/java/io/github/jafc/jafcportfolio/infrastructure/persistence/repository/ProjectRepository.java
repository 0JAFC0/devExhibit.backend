package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    @Query(value = "SELECT * FROM Project p where p.user_id = :userId",nativeQuery = true)
    public Optional<List<Project>> findProjectByUserID(Long userId);
    
    @Query(value = "SELECT * FROM project p WHERE (p.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	public Optional<List<Project>> findByEmail(@Param("email") String email);
}
