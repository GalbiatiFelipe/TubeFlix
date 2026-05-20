package com.tubetv.service;

import com.tubetv.entity.User;
import com.tubetv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; //Necessário para fazer a criptografia da senha

    public User register(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

}
