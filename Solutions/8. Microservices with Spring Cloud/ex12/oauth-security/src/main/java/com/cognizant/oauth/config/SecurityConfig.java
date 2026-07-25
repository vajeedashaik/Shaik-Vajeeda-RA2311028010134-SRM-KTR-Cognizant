package com.cognizant.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.cognizant.oauth.security.JwtTokenFilter;

// Exercise 1: OAuth2 Login — users authenticate via OIDC provider (e.g., Google)
// Exercise 3: custom JwtTokenFilter — authenticates requests bearing our own signed JWTs
//
// Note: Exercise 2's oauth2ResourceServer().jwt() is intentionally NOT wired in here.
// It validates Bearer tokens against spring.security.oauth2.resourceserver.jwt.issuer-uri
// (a placeholder value in application.yml), and Spring Security only allows one mechanism
// to own the "Authorization: Bearer ..." header per chain: if both the resource-server's
// BearerTokenAuthenticationFilter and our own JwtTokenFilter run, the resource server's
// filter fails against the fake issuer and overrides the authentication our filter already
// set, turning every request into a 401. Exercise 3's self-issued/self-validated JWT flow
// is the one this module actually demonstrates end-to-end (see UserController#login).
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    // Exercise 1 config: enable OAuth2 login (OIDC)
    @Bean
    public SecurityFilterChain oauth2LoginFilterChain(HttpSecurity http) throws Exception {
        http
            // /login issues a JWT via a plain POST (no prior session) — exempt it from CSRF
            .csrf(csrf -> csrf.ignoringRequestMatchers("/login"))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/user", true)
            )
            // Exercise 3: run the custom JWT filter before the username/password filter so
            // requests carrying "Authorization: Bearer <token>" are authenticated via our own
            // JwtTokenProvider.
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
