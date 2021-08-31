package com.project.authorizationserver;

import com.google.gson.Gson;
import com.project.authorizationserver.model.OauthToken;
import com.project.authorizationserver.model.TokenParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {
    private final Gson gson;
    private final RestTemplate restTemplate;

    @GetMapping(value = "/callback")
    public OauthToken callbackSocial(@RequestParam String code) throws URISyntaxException {

        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8081/oauth2/callback");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), OauthToken.class);
        }
        return null;
    }
 /*   TokenParam tokenParam = new TokenParam();
        tokenParam.setCode(code);
        tokenParam.setGrant_type("authorization_code");
        tokenParam.setRedirect_uri("http://localhost:8081/oauth2/callback");
    ResponseEntity<String> response =
            restTemplate.postForEntity("http://localhost:8081/oauth/token"
                    , new HttpEntity<>(tokenParam, headers)
                    , String.class);

        return ResponseEntity.ok(gson.fromJson(response.getBody(), OauthToken.class));*/
}
