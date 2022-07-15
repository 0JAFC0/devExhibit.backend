package io.github.jafc.jafcportfolio.presentation.dto;

import io.github.jafc.jafcportfolio.domain.model.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceResponse {

    private Long id;

    private String nameCompany;

    private String post;

    private String description;

    private String duration;

    private TypeEnum type;

    private UserResponse user;
}
