package com.demo.userservice.service;

import com.demo.userservice.entity.UserDemo;
import com.demo.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        userService.getAll();
        assertEquals(1,Arrays.asList(userDemo).size());
        assertNotNull(userService.getAll());
    }

    @Test
    void getUserById() {
        Long idUser= 154L;
        when(userRepository.findById(15L)).thenReturn(Optional.ofNullable(userDemo));
        userService.getUserById(idUser);
        assertNotNull(userService.getUserById(15L));
    }

    @Test
    void save() {
        when(userRepository.save(any(UserDemo.class))).thenReturn(userDemo);
        userService.save(any(UserDemo.class));
        assertEquals(userDemo.getName(),userService.save(userDemo).getName());
        assertEquals(userDemo.getAge(),userService.save(userDemo).getAge());
        assertEquals(userDemo.getLastName(),userService.save(userDemo).getLastName());
        assertNotNull(userService.save(userDemo).getId());
    }
}