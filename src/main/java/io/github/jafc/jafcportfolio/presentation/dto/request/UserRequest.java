package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiResponse(description = "DTO para requisições para a API com algumas informações do usuario")
public class UserRequest {

    @Schema(description = "Identificador unico do usuario no banco de dados")
    private Long id;

    @Schema(description = "Nome completo do usuario")
    private String name;
    
    @Schema(description = "Senha do usuario")
    private String password;
    
    @Schema(description = "Email do usuario")
    private String email;

    @Schema(description = "Idade do usuario")
    private Integer age;

    @Schema(description = "Area que o usuario trabalha")
    private String work;

    @Schema(description = "Local que o usuario mora")
    private String liveIn;

    @Schema(description = "Foto de perfil convertida em base64 do usuario")
    private String imageBase64;

    @Schema(description = "Area de interesse do usuario")
    private String field;
    
    @Schema(description = "Um pouco sobre sua carreira do usuario")
    private String about;

    @Schema(description = "A role do usuario")
    private String role;
}
