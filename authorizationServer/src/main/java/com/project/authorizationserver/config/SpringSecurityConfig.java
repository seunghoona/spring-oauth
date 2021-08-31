package com.project.authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
/**
 * http://localhost:8081/oauth/authorize?
 * client_id=testClientId&
 * redirect_uri=http://localhost:8081/oauth2/callback&
 * response_type=code&
 * scope=read
 *
 * */
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("pass")
                .roles("USER");
    }

    /**
     * config 패키지 하위에 SpringSecurityConfig를 작성합니다.
     * <p>
     * password세팅 시에는 암호화에 대한 준비가 아직 되어있지 않으므로 NoOpPasswordEncoder를 사용하도록 세팅합니다. 인증할 회원 정보도 테스트를 위해 일단 더미로 세팅합니다. csrf는 사용 안 함 처리합니다.
     * .headers().frameOptions().disable()은 security 적용 시 h2 console 사용이 막히므로 세팅합니다.
     * oauth로 시작하는 리소스는 authorization 서버 세팅시 자동으로 생성되는 주소를 누구나 접근할 수 있게 하기 위한 세팅입니다. callback테스트를 위한 url과 h2 console용 주소도 모두 접근 가능하도록 세팅합니다. security 로그인 화면은 일단 기본 폼을 사용하도록 세팅합니다.
     */
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/oauth/**", "/oauth2/callback", "/h2-console/*").permitAll()
                .and()
                    .formLogin()
                .and()
                    .httpBasic();
    }
}
