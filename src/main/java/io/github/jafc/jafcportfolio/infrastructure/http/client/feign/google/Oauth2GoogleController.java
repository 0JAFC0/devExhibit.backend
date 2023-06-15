package io.github.jafc.jafcportfolio.infrastructure.http.client.feign.google;

import io.github.jafc.jafcportfolio.presentation.dto.request.GoogleTokenRequestBody;
import io.github.jafc.jafcportfolio.presentation.dto.response.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "GoogleAccount", url = "https://oauth2.googleapis.com")
public interface Oauth2GoogleController {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Token getToken(@RequestBody GoogleTokenRequestBody googleTokenRequestBody);
}
