package io.github.jafc.jafcportfolio.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jafc.jafcportfolio.domain.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}
