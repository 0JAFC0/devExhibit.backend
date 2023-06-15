package io.github.jafc.jafcportfolio.infrastructure.http.client.feign.google;

import io.github.jafc.jafcportfolio.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GoogleAPI", url = "https://www.googleapis.com/oauth2/v3")
public interface GoogleApisController {

    @GetMapping(value = "/userinfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    User getInformacoes(@RequestHeader("Authorization") String token);
}
