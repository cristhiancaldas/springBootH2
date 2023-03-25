package com.demo.userservice.controller;

import com.demo.userservice.entity.UserDemo;
import com.demo.userservice.service.UserService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;
    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getVersion() throws Exception {
        ResultActions response = this.mockMvc.perform(get("/user/v1/version"));
        response.andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        when(userService.getAll()).thenReturn(lstUser());
        ResultActions response = this.mockMvc.perform(get("/user/v1"));
        response.andExpect(status().isOk());
    }

    @Test
    void getAllEmpty() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        ResultActions response = this.mockMvc.perform(get("/user/v1"));
        response.andExpect(status().isNoContent());
    }

    @Test
    void getById() throws Exception {
        Long idTask=15L;
        when(userService.getUserById(idTask)).thenReturn(userMock());
        ResultActions response = mockMvc.perform(get("/user/v1/{id}", idTask));
        response.andExpect(status().isOk());
    }

    @Test
    void getByIdEmpty() throws Exception {
        Long idTask=15L;
        when(userService.getUserById(idTask)).thenReturn(null);
        ResultActions response = mockMvc.perform(get("/user/v1/{id}", idTask));
        response.andExpect(status().isNotFound());
    }

    @Test
    void save() throws Exception {
        when(userService.save(userMock())).thenReturn(userMock());
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/user/v1")
                        .content(asJsonString(userMock()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserDemo userMock(){
        UserDemo user = new UserDemo();
        user.setAge("54");
        user.setName("cristhian");
        user.setLastName("caldas");
        return  user;
    }

    private List<UserDemo> lstUser(){
        UserDemo user = userMock();
        List<UserDemo> lstUser= new ArrayList<>();
        lstUser.add(user);
        return lstUser;
    }
}