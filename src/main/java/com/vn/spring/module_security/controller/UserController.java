package com.vn.spring.module_security.controller;


import com.vn.spring.module_security.entity.User;
import com.vn.spring.module_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/hi")
    public String hello() {
        return "hello user";
    }

    @GetMapping("/getProfile")
    public ResponseEntity<?> getPro() {

        User user = userService.getCurentUser();

        return ResponseEntity.ok(user);
    }





}
