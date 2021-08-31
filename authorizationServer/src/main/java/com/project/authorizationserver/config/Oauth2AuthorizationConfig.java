package com.project.authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * <p>redirectUri
     * 인증 완료 후 이동할 클라이언트 웹 페이지 주소로 code 값을 실어서 보내줍니다.
     * </p>
     * <br/>
     *
     * <p> authorizedGrantTypes
     * 인증 방식은 총 4가지가 있습니다. 그중 authorization_code 방식이 주로 사용됩니다.
     * </p><br/>
     * <p>scopes
     * 인증 후 얻은 accessToken으로 접근할 수 있는 리소스의 범위입니다. 테스트로 read, write scope가 있다고 세팅 합니다. resource서버(api서버)에서는 해당 scope정보로 클라이언트에게 제공할 리소스를 제한하거나 노출시킵니다.
     * </p><br/>
     * <p>
     * accessTokenValiditySeconds
     * 발급된 accessToken의 유효시간(초) 입니다.
     * </p><br/>
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("testClientId")
                .secret("{noop}testSecret")
                .redirectUris("http://localhost:8081/oauth2/callback")
                .authorizedGrantTypes("authorization_code")
                .scopes("read", "write")
                .accessTokenValiditySeconds(30000);
    }
}
