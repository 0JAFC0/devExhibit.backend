package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jafc.jafcportfolio.domain.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    @Query(value = "SELECT * FROM Review r where r.user_id = :userId",nativeQuery = true)
    public List<Review> findReviewsByUserID(Long userId);
    
    @Query(value = "SELECT * FROM review r WHERE (r.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	public Optional<List<Review>> findByEmail(@Param("email") String email);
}
