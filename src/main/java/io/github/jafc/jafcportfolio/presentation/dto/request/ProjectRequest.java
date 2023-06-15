package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.github.jafc.jafcportfolio.domain.enumeration.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    
    @Schema(description = "Id do projeto salvo no banco")
    private Long id;

    @Schema(description = "Nome do projeto ou o nome do repositorio do projeto")
    private String name;

    @Schema(description = "Descrição do projeto")
    private String description;

    @Schema(description = "Categoria do projeto do tipo do enum")
    private Category category;

    @Schema(description = "Imagem do projeto convertido em Base64")
    private String imageBase64;

    @Schema(description = "URl do Repositorio hospedado no github")
    private String urlProjectGithub;
}
