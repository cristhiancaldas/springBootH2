package com.demo.userService.controller;

import com.demo.userService.entity.UserDemo;
import com.demo.userService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private UserController userController;
    @Mock
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUser() throws Exception {
        when(userService.getAll()).thenReturn(usersDemo());
        ResultActions response = this.mockMvc.perform(get("/user/v1"));
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getUserById() throws Exception {
        long userId = 15L;
        when(userService.getUserById(userId)).thenReturn(Optional.of(userDemoMock()));
        ResultActions response = mockMvc.perform(get("/user/v1/{id}", userId));
        response.andExpect(status().isOk()).andDo(print());
    }

    @Test
    void deleteUser() throws Exception {
        long userId= 15L;
        doNothing().when(userService).deleteUser(userId);
        ResultActions response = mockMvc.perform(delete("/user/v1/{id}", userId));
        response.andExpect(status().isOk()).andDo(print());
    }

    @Test
    void addUser() throws Exception {

        when(userService.save(userDemoMock())).thenReturn(userDemoMock());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/user/v1")
                        .content(asJsonString(userDemoMock()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
              //  .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateUser() {
    }

    private UserDemo userDemoMock(){
        UserDemo userDemo = new UserDemo();
        userDemo.setAge("45");
        userDemo.setId(1258L);
        userDemo.setName("Cris");
        userDemo.setLastName("Jose");
        return userDemo;
    }
    private List<UserDemo> usersDemo(){

        UserDemo userDemo01 = new UserDemo();
        userDemo01.setId(1258L);
        userDemo01.setName("Cris");
        userDemo01.setLastName("Jose");
        userDemo01.setAge("45");

        UserDemo userDemo02 = new UserDemo();
        userDemo02.setId(1259L);
        userDemo02.setName("Juan");
        userDemo02.setLastName("Perez");
        userDemo02.setAge("25");

        List<UserDemo> usersDemo = new ArrayList<>();
        usersDemo.add(userDemo01);
        usersDemo.add(userDemo02);
        return usersDemo;
    }
}