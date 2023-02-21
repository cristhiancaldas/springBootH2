package com.demo.userService.controller;

import com.demo.userService.entity.UserDemo;
import com.demo.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserDemo addUser(@RequestBody UserDemo user) {
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDemo>> getAllUser() {
        List<UserDemo> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDemo> getUserById(@PathVariable("id") Long id) {
        Optional<UserDemo> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDemo> updateUser(@PathVariable("id") long id, @RequestBody UserDemo userBody) {
        Optional<UserDemo> user = userService.getUserById(id);

        if (user.isPresent()) {
            UserDemo _user = user.get();
            _user.setName(userBody.getName());
            _user.setAge(userBody.getAge());
            _user.setLastName(userBody.getLastName());
            return new ResponseEntity<>(userService.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
