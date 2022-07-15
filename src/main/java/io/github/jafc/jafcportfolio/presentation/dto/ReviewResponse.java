package io.github.jafc.jafcportfolio.presentation.dto;

import java.util.Base64;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    
    private Long id;

    private String title;

    private String content;

    private Base64 image;

    private UserResponse user;

}
