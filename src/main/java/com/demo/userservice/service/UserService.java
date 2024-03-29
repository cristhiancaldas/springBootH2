package com.demo.userservice.service;

import com.demo.userservice.entity.UserDemo;
import com.demo.userservice.repository.UserRepository;
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
        return userRepository.save(user);
    }
}
