package io.github.jafc.jafcportfolio.infrastructure.http.client.feign.github;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "github-api", url = "https://api.github.com")
public interface GithubApiController {

    @GetMapping(value = "/user", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> getInformations(@RequestHeader("Authorization") String token);
}
