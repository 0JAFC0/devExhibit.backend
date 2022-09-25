package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.github.jafc.jafcportfolio.domain.model.TypeSkillEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
    
    private String name;

    private Integer percentagem;

    private TypeSkillEnum type;
    
    private UserRequest user;
}
