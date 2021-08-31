package com.project.authorizationserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenParam {
    private String code;
    private String grant_type;
    private String redirect_uri;
}
