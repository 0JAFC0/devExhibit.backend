package io.github.jafc.jafcportfolio.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalExperienceRequest {
    
    private String name;

    private String post;

    private String description;

    private String duration;

    private UserRequest user;
}
