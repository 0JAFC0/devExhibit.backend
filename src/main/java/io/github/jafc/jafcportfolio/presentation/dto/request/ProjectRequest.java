package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.github.jafc.jafcportfolio.domain.model.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    
    @ApiModelProperty(notes = "Id do projeto salvo no banco")
    private Long id;

    @ApiModelProperty(notes = "Nome do projeto ou o nome do repositorio do projeto")
    private String name;

    @ApiModelProperty(notes = "Descrição do projeto")
    private String description;

    @ApiModelProperty(notes = "Categoria do projeto do tipo do enum")
    private CategoryEnum category;

    @ApiModelProperty(notes = "Imagem do projeto convertido em Base64")
    private String imageBase64;

    @ApiModelProperty(notes = "URl do Repositorio hospedado no github")
    private String urlProjectGithub;

    @ApiModelProperty(notes = "Objeto usuario nele vai conter informações como id")
    private UserRequest user;
}
