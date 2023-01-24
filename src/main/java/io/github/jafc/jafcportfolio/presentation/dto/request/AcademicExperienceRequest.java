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
public class AcademicExperienceRequest {
    
    @ApiModelProperty(notes = "Id da experiencia academica salva no banco")
    private Long id;

    @ApiModelProperty(notes = "Nome do curso ou graduação")
    private String name;

    @ApiModelProperty(notes = "Nome da instituição")
    private String institution;

    @ApiModelProperty(notes = "Descrição da experiencia")
    private String description;

    @ApiModelProperty(notes = "Duração da experiencia academica")
    private String duration;
    
    @ApiModelProperty(notes = "Objeto usuario nele vai conter informações como id")
    private UserRequest user;
}
