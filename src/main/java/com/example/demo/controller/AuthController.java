package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.dto.RegisterResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        String token =
                service.login(
                        request.getEmail(),
                        request.getPassword()
                );

        String role =
                service.getRoleByEmail(
                        request.getEmail()
                );

        AuthResponse response = new AuthResponse();

        response.setToken(token);
        response.setRole(role);

        return response;
    }
    
    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        return service.register(dto);
    }
}
