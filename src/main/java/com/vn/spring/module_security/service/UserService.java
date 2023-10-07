package com.vn.spring.module_security.service;


import com.vn.spring.module_security.entity.Role;
import com.vn.spring.module_security.entity.User;
import com.vn.spring.module_security.request.LoginRequest;
import com.vn.spring.module_security.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String authenticate(LoginRequest loginDto);

    ResponseEntity<?> register (RegisterRequest registerDto);

    Role saveRole(Role role);

    User saverUser (User user) ;

    Optional<User> findByEmail(String email);


    public List<User> getAllAdmin(Integer roleId);



    public Role getRole(Integer roleId);

    User findUserById(Long id);

    void deleteUser(User user);

    User getCurentUser();


}

