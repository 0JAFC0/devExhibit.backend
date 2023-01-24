package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalExperienceRequest {
    
    @ApiModelProperty(notes = "Id da experiencia profissional salva no banco")
    private Long id;

    @ApiModelProperty(notes = "Nome da area de atuação")
    private String name;

    @ApiModelProperty(notes = "Cargo de atuação")
    private String post;

    @ApiModelProperty(notes = "Descrição da experiencia profissional")
    private String description;

    @ApiModelProperty(notes = "Duração da experiencia profissional em anos")
    private String duration;

    @ApiModelProperty(notes = "Objeto usuario nele vai conter informações como id")
    private UserRequest user;
}
