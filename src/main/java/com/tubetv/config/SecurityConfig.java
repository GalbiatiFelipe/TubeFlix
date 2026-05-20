package com.tubetv.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
/*
* @Configuration: diz que a classe é para configurações.
*
* @EnableWebSecurity: habilita o sistema que gerencia os metodos que serão criados
* para configurar o spring security.
* */
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean //Diz que o spring precisa gerenciar este método, pois é o metodo que faz a segurança
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /* csrf.disable(): Desabilita a configuração padrão do spring.
                *
                * SessionCreationPolicy.STATELESS: toda chamada para a aplicação deve ser verificada, se vem de uma origem válida (ex: usuário logado),
                * ou se a chamada está sem validação.
                * */
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers(HttpMethod.POST, "/tubetv/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tubetv/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger/**").permitAll() //permissoes para o swagger
                        .anyRequest().authenticated()
                )
                /*
                * requestMatchers
                *  .permitAll(): autoriza o endpoint para que todos possam fazer a requisição http.
                *
                * anyRequest: seta uma configuração para todos os outros request não especificados, neste caso 'authenticated()'
                * requer que todos sejam autenticados para serem aceitos.
                *
                * */
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
        // metodo que faz o processo de autentificação, portanto ao iniciar a aplicação não teremos mais a mensagem de senha padrao
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /*
        * new BCryptPasswordEncoder(): quando houver uma chamada para 'passwordEnconder' o retorno será este
        * */
    }


}
