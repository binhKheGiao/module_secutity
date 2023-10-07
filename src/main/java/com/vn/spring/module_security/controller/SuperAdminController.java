package com.vn.spring.module_security.controller;

import com.vn.spring.module_security.entity.User;
import com.vn.spring.module_security.request.UserResponse;
import com.vn.spring.module_security.service.RoleService;
import com.vn.spring.module_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/superadmin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final UserService userService;

    private final RoleService roleService;



    @GetMapping("/hello")
    public String hello() {
        return "hello superAdmin";
    }

    @GetMapping("/get-all-user/{id}")
    public ResponseEntity<?> getUserByRoleId(@PathVariable Integer id) {
        List<User> userList = userService.getAllAdmin(id);
        return ResponseEntity.ok(userList);
    }


    @PutMapping("/add-role-user")
    public ResponseEntity<User> addRoleToUser(@RequestBody UserResponse userResponse) {

        User user = userService.findByEmail(userResponse.getEmail()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(400).body(null);
        }
        user.getRoleList().clear();

        for (Integer roleId : userResponse.getRoleId()) {
            var role = roleService.findRoleById(roleId);
            if (role != null) {
                user.getRoleList().add(role);
            }
        }
        user = userService.saverUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/del-user/{id}")
    public  ResponseEntity<?> delUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
        return ResponseEntity.ok(user);
    }




}

