package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.github.jafc.jafcportfolio.domain.model.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    
    private Long id;

    private String name;

    private String description;

    private CategoryEnum category;

    private String imageBase64;

    private String urlProjectGithub;

    private UserRequest user;
}
