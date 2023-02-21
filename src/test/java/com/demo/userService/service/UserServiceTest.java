package com.demo.userService.service;

import com.demo.userService.entity.UserDemo;
import com.demo.userService.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDemo userDemo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDemo= new UserDemo();
        userDemo.setAge("45");
        userDemo.setId(1258L);
        userDemo.setName("Cristhian");
        userDemo.setLastName("Jose");
    }

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(userDemo));
        assertEquals(1,Arrays.asList(userDemo).size());
        assertNotNull(userService.getAll());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(eq(15L))).thenReturn(Optional.ofNullable(userDemo));
        assertNotNull(userService.getUserById(15L));
    }

    @Test
    void save() {
        when(userRepository.save(any(UserDemo.class))).thenReturn(userDemo);
        assertEquals(userDemo.getName(),userService.save(userDemo).getName());
    }
}