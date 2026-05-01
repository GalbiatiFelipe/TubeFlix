package com.tubetv.controller;

import com.tubetv.config.TokenService;
import com.tubetv.controller.request.LoginRequest;
import com.tubetv.controller.request.UserRequest;
import com.tubetv.controller.response.LoginResponse;
import com.tubetv.controller.response.UserResponse;
import com.tubetv.entity.User;
import com.tubetv.mapper.UserMapper;
import com.tubetv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tubetv/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager; // injetando metodo de SecurityConfig
    private final TokenService tokenService; //implementação da classe token service para poder ser utilizada no endpoint de login

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        User registeredUser = userService.register(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        // Faz a busca por baixo dos panos do usuario e se existir passa para o 'authentication'
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();
        // utilizamos um cast pois sabemos que 'authentication' é um User pois no 'AuthService' o retorno do meotodo é um UserDetail, que portanto sai da entidade User"
        String token = tokenService.generateToken(user); //passamos o usuario encontrado para gerar o token da maneira que configuramos na tokenService.

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
