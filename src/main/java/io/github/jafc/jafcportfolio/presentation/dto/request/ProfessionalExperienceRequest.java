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
public class ProfessionalExperienceRequest {
    
    @Schema(description = "Id da experiencia profissional salva no banco")
    private Long id;

    @Schema(description = "Nome da area de atuação")
    private String name;

    @Schema(description = "Cargo de atuação")
    private String post;

    @Schema(description = "Descrição da experiencia profissional")
    private String description;

    @Schema(description = "Duração da experiencia profissional em anos")
    private String duration;
}
