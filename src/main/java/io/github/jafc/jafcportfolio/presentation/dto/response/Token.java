package io.github.jafc.jafcportfolio.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {

	@JsonAlias("access_token")
	private String accessToken;

	@JsonAlias("token_type")
	private String tokenType;

	private String scope;

	private Long id;

	private List<String> roles;
}
