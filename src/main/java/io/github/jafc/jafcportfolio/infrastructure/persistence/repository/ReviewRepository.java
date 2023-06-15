package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.jafc.jafcportfolio.domain.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM Review r where r.user_id = :userId",nativeQuery = true)
    Optional<List<Review>> findReviewsByUserID(Long userId);
    
    @Query(value = "SELECT * FROM review r WHERE (r.user_id = (SELECT id FROM users u WHERE u.email = :email))", nativeQuery = true)
	Optional<List<Review>> findByEmail(@Param("email") String email);

    void deleteReviewByTitleAndAndUser_Email(String title, String email);
}
