package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jafc.jafcportfolio.application.services.ReviewServices;
import io.github.jafc.jafcportfolio.domain.model.Review;
import io.github.jafc.jafcportfolio.infrastructure.utils.httpResponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelMapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.response.ReviewResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.annotations.Api;

@Api(value = "End Point do review")
@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:4200/")
public class ReviewController {
    
    @Autowired
    private ReviewServices reviewServices;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ReviewResponse>> save(@RequestBody ReviewResponse reviewResponse) {
        return responseService.create(modelMapperService.convert(reviewServices.saveReviewUser(modelMapperService.convert(reviewResponse, Review.class)),ReviewResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteSkill(@RequestBody ReviewResponse review) {
        reviewServices.removeReviewUser(modelMapperService.convert(review, Review.class));
        return responseService.ok("delete Successful");
    }
    
    @PutMapping
    public ResponseEntity<Response<ReviewResponse>> update(@RequestBody ReviewResponse review) {
        return responseService.ok(
            modelMapperService.convert(reviewServices.updateReviewUser(
                modelMapperService.convert(review, Review.class)), ReviewResponse.class));
    }

    @GetMapping
    public ResponseEntity<Response<List<ReviewResponse>>> getAll() {
        List<ReviewResponse> dtos = reviewServices.getAll().stream()
            .map(review -> modelMapperService.convert(review, ReviewResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }

    @GetMapping("/getReviewByUserID/{userId}")
    public ResponseEntity<Response<List<ReviewResponse>>> getReviewByUserID(@PathVariable("userId") Long userId) {
        List<ReviewResponse> dtos = reviewServices.getReviewsByUserID(userId).stream()
            .map(review -> modelMapperService.convert(review, ReviewResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ReviewResponse>>> getByEmail(@PathVariable("email") String email) {
        List<ReviewResponse> dtos = reviewServices.getByEmail(email).stream()
            .map(review -> modelMapperService.convert(review, ReviewResponse.class))
            .collect(Collectors.toList());
        return responseService.ok(dtos);
    }
}
