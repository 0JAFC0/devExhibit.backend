package io.github.jafc.jafcportfolio.application.controllers;

import java.util.List;

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
import io.github.jafc.jafcportfolio.infrastructure.utils.httpresponse.ResponseService;
import io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper.ModelMapperService;
import io.github.jafc.jafcportfolio.presentation.dto.request.ReviewRequest;
import io.github.jafc.jafcportfolio.presentation.dto.response.ReviewResponse;
import io.github.jafc.jafcportfolio.presentation.shared.Response;
import io.swagger.annotations.Api;

@Api(value = "End Point do review")
@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = {"http://127.0.0.1:4200/","https://0jafc0.github.io/"})
public class ReviewController {
    
    @Autowired
    private ReviewServices reviewServices;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/save")
    public ResponseEntity<Response<ReviewResponse>> save(@RequestBody ReviewRequest reviewResponse) {
        return responseService.create(modelMapperService.convert(reviewServices.saveReviewUser(modelMapperService.convert(reviewResponse, Review.class)),ReviewResponse.class));
    }

    @DeleteMapping
    public ResponseEntity<Response<String>> deleteSkill(@RequestBody ReviewRequest review) {
        reviewServices.removeReviewUser(modelMapperService.convert(review, Review.class));
        return responseService.ok("delete Successful");
    }
    
    @PutMapping
    public ResponseEntity<Response<ReviewResponse>> update(@RequestBody ReviewRequest review) {
        return responseService.ok(
            modelMapperService.convert(reviewServices.updateReviewUser(
                modelMapperService.convert(review, Review.class)), ReviewResponse.class));
    }

    @GetMapping("/reviews")
    public ResponseEntity<Response<List<ReviewResponse>>> getAll() {
        return responseService.ok(modelMapperService.convertList(reviewServices.getAll(), ReviewResponse.class));
    }

    @GetMapping("/getReviewsByUserId/{userId}")
    public ResponseEntity<Response<List<ReviewResponse>>> getReviewByUserID(@PathVariable("userId") Long userId) {
        return responseService.ok(modelMapperService.convertList(reviewServices.getReviewsByUserID(userId), ReviewResponse.class));
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Response<List<ReviewResponse>>> getByEmail(@PathVariable("email") String email) {
        return responseService.ok(modelMapperService.convertList(reviewServices.getByEmail(email), ReviewResponse.class));
    }
}
