package com.gabriel.reservaareacomum.infra.config;

import com.gabriel.reservaareacomum.application.auth.AuthUseCase;
import com.gabriel.reservaareacomum.infra.persistence.repositories.UserRepository;
import com.gabriel.reservaareacomum.infra.providers.BCryptPasswordEncoderAdapter;
import com.gabriel.reservaareacomum.infra.providers.JwtEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Arrays;

@Configuration
public class AuthConfig {
    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtEncoder jwtEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        if(Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            http.headers().frameOptions().disable();
        }

        http.cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers( "/api/auth").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new AuthFilter(jwtEncoder), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthUseCase createAuthUseCase() {
        return new AuthUseCase(userRepository, new BCryptPasswordEncoderAdapter());
    }
}
