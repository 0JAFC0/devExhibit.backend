package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    @Query(value = "SELECT * FROM Review r where r.user_id = :userId",nativeQuery = true)
    public List<Review> findReviewsByUserID(Long userId);
}
