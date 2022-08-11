package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    @Query(value = "SELECT * FROM Project p where p.user_id = :userId",nativeQuery = true)
    public List<Project> findProjectByUserID(Long userId);
}
