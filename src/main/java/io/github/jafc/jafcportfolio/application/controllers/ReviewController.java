package io.github.jafc.jafcportfolio.application.controllers;

import io.github.jafc.jafcportfolio.application.services.ReviewServices;
import io.github.jafc.jafcportfolio.domain.model.Review;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.ReviewRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.ReviewResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.REVIEW_URI;

@Tag(name = "review-controller", description = "End Point do review")
@RestController
@RequestMapping(REVIEW_URI)
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/","https://0jafc0.github.io/"})
public class ReviewController {

    private final ReviewServices reviewServices;

    private final ModelMapperService modelMapperService;

    private final ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ReviewResponse>> save(@RequestBody ReviewRequest reviewResponse,
                                                         Principal principal) {

        Review review = reviewServices.saveReviewUser(modelMapperService.convert(reviewResponse, Review.class),
                                                      principal.getName());
        return responseService.create(modelMapperService.convert(review, ReviewResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteSkill(@RequestBody ReviewRequest review, Principal principal) {
        reviewServices.removeReviewUser(modelMapperService.convert(review, Review.class), principal.getName());
        return responseService.ok("delete Successful");
    }
    
    @PutMapping
    public ResponseEntity<Response<ReviewResponse>> update(@RequestBody ReviewRequest reviewRequest,
                                                           Principal principal) {

        Review review = reviewServices.updateReviewUser(modelMapperService.convert(reviewRequest, Review.class),
                                                        principal.getName());

        return responseService.ok(modelMapperService.convert(review, ReviewResponse.class));
    }

    @GetMapping("/get-reviews-By-user-id/{userId}")
    public ResponseEntity<Response<List<ReviewResponse>>> getReviewByUserID(@PathVariable("userId") Long userId) {
        return responseService.ok(modelMapperService.convertList(reviewServices.getReviewsByUserID(userId), ReviewResponse.class));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ReviewResponse>>> getByEmail(@PathVariable("email") String email) {
        return responseService.ok(modelMapperService.convertList(reviewServices.getByEmail(email), ReviewResponse.class));
    }
}
