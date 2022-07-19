package io.github.jafc.jafcportfolio.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.Review;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ReviewRepository;

@Service
public class ReviewServices {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
