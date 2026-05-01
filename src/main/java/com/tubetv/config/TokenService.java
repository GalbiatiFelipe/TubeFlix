package com.tubetv.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tubetv.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${tubetv.security.secret}") //caminho do application
    private String secret; // a 'secret' será implementada no 'application.propertie' para ficar mais seguro

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret); // algoritimo com a 'palavra-secreta' que vai ser passado no '.sign'

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))//tempo de experição do login
                .withIssuedAt(Instant.now()) // quando foi gerado este token
                .withIssuer("API TubeTv") // quem gerou este token
                /*
                * Formas de pegar informações pelo token e configurar as informações de login
                * E no fim criptografa e retorna o token
                * */
                .sign(algorithm);
    }

}
