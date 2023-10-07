package com.vn.spring.module_security.controller;

import com.vn.spring.module_security.request.LoginRequest;
import com.vn.spring.module_security.request.RegisterRequest;
import com.vn.spring.module_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final UserService userService;


    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest registerDto) {
        return  userService.register(registerDto);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody LoginRequest loginDto) {
        String token =   userService.authenticate(loginDto);
        return token;
    }



}
