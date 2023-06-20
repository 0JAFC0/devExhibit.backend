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

        Review review = reviewServices.save(modelMapperService.convert(reviewResponse, Review.class),
                                                      principal.getName());
        return responseService.create(modelMapperService.convert(review, ReviewResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> delete(@RequestBody ReviewRequest review, Principal principal) {
        reviewServices.remove(modelMapperService.convert(review, Review.class), principal.getName());
        return responseService.ok("delete Successful");
    }
    
    @PutMapping
    public ResponseEntity<Response<ReviewResponse>> update(@RequestBody ReviewRequest reviewRequest,
                                                           Principal principal) {

        Review review = reviewServices.update(modelMapperService.convert(reviewRequest, Review.class),
                                                        principal.getName());

        return responseService.ok(modelMapperService.convert(review, ReviewResponse.class));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response<List<ReviewResponse>>> getByUserID(@PathVariable Long userId) {
        return responseService.ok(modelMapperService.convertList(reviewServices.getReviewsByUserId(userId),
                                                                 ReviewResponse.class));
    }
}
