package io.github.jafc.jafcportfolio.infrastructure.http.client.feign.github;

import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "github", url = "https://github.com/login/oauth")
public interface GithubBatchController {

    @PostMapping(value = "/access_token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Token> getToken(@RequestParam("client_id") String clientId,
                                   @RequestParam("client_secret") String clientSecret,
                                   @RequestParam String code,
                                   @RequestParam("redirect_uri") String redirectUri);

}
