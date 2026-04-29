package com.tubetv.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean //Diz que o spring precisa gerenciar este método, pois é o metodo que faz a segurança
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //Desabilita a configuração padrão do spring, ja que precisamos configurar a propria
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // toda chamada que ocorrer será verificada se vem de alguém válida, ou logado
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/tubetv/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tubetv/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                //.addFilter()
                /*
                * requestMatchers:
                *  autoriza o endpoint para que todos possam fazer a requisição http
                * anyRequest:
                *  seta uma configuração para todos os outros request não especificados, neste caso 'authenticated()'
                *  requer que todos sejam autenticados para serem aceitos
                * */
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
