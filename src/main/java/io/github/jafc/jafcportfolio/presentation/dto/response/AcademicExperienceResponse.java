package io.github.jafc.jafcportfolio.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicExperienceResponse {

    private Long id;

    private String name;

    private String institution;

    private String description;

    private String duration;
    
    private UserResponse user;
}
