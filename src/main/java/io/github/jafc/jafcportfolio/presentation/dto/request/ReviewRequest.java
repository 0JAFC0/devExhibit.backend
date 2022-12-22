package io.github.jafc.jafcportfolio.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    
    private Long id;

    private String title;

    private String content;

    private String imageBase64;

    private UserRequest user;

}
