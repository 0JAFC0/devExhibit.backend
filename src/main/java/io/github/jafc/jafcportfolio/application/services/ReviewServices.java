package io.github.jafc.jafcportfolio.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jafc.jafcportfolio.domain.model.Review;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ReviewRepository;

@Service
public class ReviewServices {
    
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    public Review saveReviewUser(Review review) {
        User user = userService.findById(review.getUser().getId());
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public Review updateReviewUser(Review review) {
        Optional<Review> temp = reviewRepository.findById(review.getId());
        if(!temp.isPresent()) {
            throw new NotFoundException("Not found review with id".concat(review.getId().toString()));
        }
        return reviewRepository.save(review);
    }

    public void removeReviewUser(Review review) {
        User user = userService.findById(review.getUser().getId());
        if(!(review.getUser().getId().equals(user.getId()))) {
            throw new NotFoundException("Not found review in user with id " + review.getUser().getId());
        }
        reviewRepository.deleteById(review.getId());
    }

    public List<Review> getReviewsByUserID(Long userId) {
        List<Review> reviews = reviewRepository.findReviewsByUserID(userId);
        if(reviews.isEmpty()) {
            throw new NotFoundException("Not found review in user with id ".concat(userId.toString()));
        }
        return reviews;
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
