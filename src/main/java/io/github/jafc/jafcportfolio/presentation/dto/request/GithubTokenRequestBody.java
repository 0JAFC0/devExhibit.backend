package io.github.jafc.jafcportfolio.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GithubTokenRequestBody {

    private String client_id;

    private String client_secret;

    private String code;

    private String redirect_uri;
}
