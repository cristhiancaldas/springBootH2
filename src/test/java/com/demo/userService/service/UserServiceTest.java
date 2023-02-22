package com.demo.userService.service;

import com.demo.userService.entity.UserDemo;
import com.demo.userService.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDemo userDemo;

    @BeforeEach
    void setUp() {
        openMocks(this);
        userDemo = userDemoMock();
    }

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(userDemo));
        userService.getAll();
        verify(userRepository, times(1)).findAll();
        assertEquals(1,Arrays.asList(userDemo).size());
        assertNotNull(userService.getAll());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(eq(15L))).thenReturn(Optional.ofNullable(userDemo));
        userService.getUserById(15L);
        verify(userRepository, times(1)).findById(eq(15L));
    }

    @Test
    void save() {
        when(userRepository.save(any(UserDemo.class))).thenReturn(userDemo);
        UserDemo addUser= userService.save(userDemo);
        assertEquals(userDemo.getName(),addUser.getName());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void delete(){
        doNothing().when(userRepository).deleteById(eq(15L));
        userService.deleteUser(eq(15L));
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    private UserDemo userDemoMock(){
        UserDemo userDemo = new UserDemo();
        userDemo.setAge("45");
        userDemo.setId(1258L);
        userDemo.setName("Cris");
        userDemo.setLastName("Jose");
        return userDemo;
    }
}