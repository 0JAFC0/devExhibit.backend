package io.github.jafc.jafcportfolio.presentation.dto;

import java.util.Base64;

import io.github.jafc.jafcportfolio.domain.model.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    
    private Long id;

    private String name;

    private String description;

    private CategoryEnum category;

    private Base64 image;

    private String urlProjectGithub;

    private UserResponse user;
}
