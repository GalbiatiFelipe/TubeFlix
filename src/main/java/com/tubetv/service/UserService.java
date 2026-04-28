package com.tubetv.service;

import com.tubetv.controller.request.UserRequest;
import com.tubetv.entity.User;
import com.tubetv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

}
