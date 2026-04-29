package com.tubetv.service;

import com.tubetv.controller.request.UserRequest;
import com.tubetv.entity.User;
import com.tubetv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        // vai pegar diretamente a senha digitada pelo usuario e criptografa-la
        return userRepository.save(user);
    }

}
