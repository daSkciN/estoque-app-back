package com.estoqueApp.estoqueApp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // permite acesso ao Swagger sem autenticação
                .antMatchers("/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**").permitAll()
                // endpoints públicos da sua API
                .antMatchers("/actuator/health").permitAll()
                .antMatchers("/auth", "/usuario/current").permitAll()
                .antMatchers("/produto", "/produto/**", "/entradaEstoque", "/entradaEstoque/**", "/venda", "/venda/**, /categoria", "/categoria/**").permitAll()
                // tudo mais exige autenticação
                .anyRequest().authenticated();
                //.and()
                //.httpBasic(); // ou formLogin(), dependendo do que você usa

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "https://estoque-app-back-1.onrender.com")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
        };
    }

}
