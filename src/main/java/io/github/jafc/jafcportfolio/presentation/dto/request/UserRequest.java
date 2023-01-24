package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO para requisições para a API com algumas informações do usuario")
public class UserRequest {

    @ApiModelProperty(notes = "Identificador unico do usuario no banco de dados")
    private Long id;

    @ApiModelProperty(notes = "Nome completo do usuario")
    private String fullname;
    
    @ApiModelProperty(notes = "Senha do usuario")
    private String password;
    
    @ApiModelProperty(notes = "Email do usuario")
    private String email;

    @ApiModelProperty(notes = "Idade do usuario")
    private Integer age;

    @ApiModelProperty(notes = "Area que o usuario trabalha")
    private String work;

    @ApiModelProperty(notes = "Local que o usuario mora")
    private String liveIn;

    @ApiModelProperty(notes = "Foto de perfil convertida em base64 do usuario")
    private String imageBase64;

    @ApiModelProperty(notes = "Area de interesse do usuario")
    private String field;
    
    @ApiModelProperty(notes = "Um pouco sobre sua carreira do usuario")
    private String about;
}
