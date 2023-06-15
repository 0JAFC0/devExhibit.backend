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
public class AcademicExperienceRequest {
    
    @Schema(description = "Id da experiencia academica salva no banco")
    private Long id;

    @Schema(description = "Nome do curso ou graduação")
    private String name;

    @Schema(description = "Nome da instituição")
    private String institution;

    @Schema(description = "Descrição da experiencia")
    private String description;

    @Schema(description = "Duração da experiencia academica")
    private String duration;
}
