package com.demo.userservice.controller;

import com.demo.userservice.entity.UserDemo;
import com.demo.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/version")
    public String getVersion(){
        return "This is version 1.0.2";
    }

    @GetMapping
    public ResponseEntity<List<UserDemo>> getAll() {
        List<UserDemo> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDemo> getById(@PathVariable("id") Long id) {
        UserDemo user = userService.getUserById(id);
        if(Objects.isNull(user))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDemo save(@RequestBody UserDemo user) {
        return userService.save(user);
    }
}
