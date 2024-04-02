package com.app.app.config;

import com.app.app.config.filter.JwtTokenValidator;
import com.app.app.dto.Error;
import com.app.app.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Date;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf ->csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar endpoints publicos
                    http.requestMatchers(HttpMethod.POST, "auth/**").permitAll();
                    //configurar endpoints privados
                    http.requestMatchers(HttpMethod.GET, "/method/**").hasAuthority("READ");
                    //configurar endpoiints no especificados
                   // http.anyRequest().denyAll();
                })
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler()))
                .authorizeHttpRequests(http -> http.anyRequest().denyAll())
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
       // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();

    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Error errorResponse = new Error();
            errorResponse.setDate(new Date());
            errorResponse.setMessage("No autorizado");
            errorResponse.setError(authException.getMessage());
            errorResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Error errorResponse = new Error();
            errorResponse.setDate(new Date());
            errorResponse.setMessage("Acceso denegado");
            errorResponse.setError(accessDeniedException.getMessage());
            errorResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        };
    }


}
