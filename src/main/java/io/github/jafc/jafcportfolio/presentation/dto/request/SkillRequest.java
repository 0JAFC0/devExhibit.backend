package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.github.jafc.jafcportfolio.domain.model.TypeSkillEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
    
    @ApiModelProperty(notes = "Id da habilidade")
    private Long id;

    @ApiModelProperty(notes = "Nome da habilidade")
    private String name;

    @ApiModelProperty(notes = "Percentual de dominio da habilidade")
    private Integer percentagem;

    @ApiModelProperty(notes = "Tipo de habilidade")
    private TypeSkillEnum type;
    
    @ApiModelProperty(notes = "Objeto usuario nele vai conter informações como id")
    private UserRequest user;
}
