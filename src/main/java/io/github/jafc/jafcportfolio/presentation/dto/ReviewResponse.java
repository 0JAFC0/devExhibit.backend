package io.github.jafc.jafcportfolio.presentation.dto;

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

    private String imageBase64;

    private UserResponse user;

}
