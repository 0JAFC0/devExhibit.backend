package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiResponse(description = "DTO de requisição de login para a API com algumas informações do usuario")
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentials implements Serializable{
	
	@Schema(description = "email do usuario")
	private String email;
	
	@Schema(description = "senha do usuario")
	private String password;
}
