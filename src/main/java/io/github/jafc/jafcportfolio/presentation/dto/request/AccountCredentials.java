package io.github.jafc.jafcportfolio.presentation.dto.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "DTO de requisição de login para a API com algumas informações do usuario")
public class AccountCredentials implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "email do usuario")
	private String email;
	
	@ApiModelProperty(notes = "senha do usuario")
	private String password;
}
