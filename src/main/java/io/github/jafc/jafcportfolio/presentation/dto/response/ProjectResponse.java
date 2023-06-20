package io.github.jafc.jafcportfolio.presentation.dto.response;

import io.github.jafc.jafcportfolio.domain.enumeration.Category;
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

    private Category category;

    private String imageBase64;

    private String urlProject;

    private UserResponse user;
}
