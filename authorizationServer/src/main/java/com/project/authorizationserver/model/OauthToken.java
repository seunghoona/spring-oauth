package com.project.authorizationserver.model;


import antlr.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OauthToken {
    private String access_token;
    private TokenType token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
}
