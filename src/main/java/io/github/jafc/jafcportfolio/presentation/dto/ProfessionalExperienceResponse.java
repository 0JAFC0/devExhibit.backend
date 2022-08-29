package io.github.jafc.jafcportfolio.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalExperienceResponse {

    private Long id;

    private String name;

    private String post;

    private String description;

    private String duration;

    private UserResponse user;
}
