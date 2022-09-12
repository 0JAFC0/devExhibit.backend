package io.github.jafc.jafcportfolio.presentation.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String type = "Bearer";
	private String accessToken;
	private String email;
	private List<String> roles;
}
