package io.github.jafc.jafcportfolio.application.services;

import io.github.jafc.jafcportfolio.domain.model.Review;
import io.github.jafc.jafcportfolio.domain.model.User;
import io.github.jafc.jafcportfolio.infrastructure.exceptions.NotFoundException;
import io.github.jafc.jafcportfolio.infrastructure.persistence.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServices {

    private final ReviewRepository reviewRepository;

    private final UserService userService;

    public Review save(Review review, String email) {
        User user = userService.getUserByEmail(email);
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public Review update(Review review, String email) {
        User user = userService.getUserByEmail(email);

        Optional<Review> temp = reviewRepository.findById(review.getId());
        if(temp.isEmpty()) {
            throw new NotFoundException("Not found review with id".concat(review.getId().toString()));
        }
        review.setUser(user);

        return reviewRepository.save(review);
    }

    public void remove(Review review, String email) {
        reviewRepository.deleteReviewByTitleAndAndUser_Email(review.getTitle(), email);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findReviewsByUserID(userId)
                .orElseThrow(() -> new NotFoundException("Not found review in user with id ".concat(userId.toString())));
    }
    
    public List<Review> getByEmail(String email) {
        return reviewRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("The user with email ".concat(email).concat(" not found!")));
    }
}
