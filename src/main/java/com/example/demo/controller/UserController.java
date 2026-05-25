package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public User createUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }
    
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id,
                           @Valid @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/del/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}