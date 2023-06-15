package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.github.jafc.jafcportfolio.domain.enumeration.TypeSkill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
    
    @Schema(description = "Id da habilidade")
    private Long id;

    @Schema(description = "Nome da habilidade")
    private String name;

    @Schema(description = "Percentual de dominio da habilidade")
    private Integer percentagem;

    @Schema(description = "Tipo de habilidade")
    private TypeSkill type;
}
