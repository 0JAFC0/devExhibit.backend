package io.github.jafc.jafcportfolio.presentation.dto.response;

import io.github.jafc.jafcportfolio.domain.enumeration.TypeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillResponse {

    private Long id;
    
    private String name;

    private Integer percentagem;

    private TypeSkill type;
}
