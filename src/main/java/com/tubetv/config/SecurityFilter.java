package com.tubetv.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        //O que acontece aqui é um header com o nome "Authorization" que tem como value o token do usuario que fez a requisição

        //Se o header não for vazio e começar com 'Bearer' signfica que é de fato um Token
        if (Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length()); //pega tudo do token depois do 'bearer'

            // chamando o metodo e passando o que será feito se toda a validação for correta
            Optional<JWTUserData> optJwtUserData = tokenService.verifyToken(token);
            if (optJwtUserData.isPresent()) {
                JWTUserData userData = optJwtUserData.get();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                //Basicamente esta autenticando o token.
            }

            filterChain.doFilter(request, response); //indendente da validação essa linha deve se manter, se não a requisição não vai pra frente
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
