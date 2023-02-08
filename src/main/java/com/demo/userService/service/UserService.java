package com.demo.userService.service;

import com.demo.userService.entity.UserDemo;
import com.demo.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDemo> getAll() {
        return userRepository.findAll();
    }

    public UserDemo getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDemo save(UserDemo user) {
        UserDemo userNew = userRepository.save(user);
        return userNew;
    }
}
