package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    
    @Schema(description = "Id do review salvo no banco")
    private Long id;

    @Schema(description = "Titulo do review")
    private String title;

    @Schema(description = "Conteudo do review")
    private String content;

    @Schema(description = "Imagem do review em base64")
    private String imageBase64;
}
