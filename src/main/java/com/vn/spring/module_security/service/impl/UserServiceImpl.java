package com.vn.spring.module_security.service.impl;

import com.vn.spring.module_security.entity.Role;
import com.vn.spring.module_security.entity.RoleName;
import com.vn.spring.module_security.entity.User;
import com.vn.spring.module_security.exception.JwtInvalidException;
import com.vn.spring.module_security.exception.UserAlreadyExistsException;
import com.vn.spring.module_security.repository.IRoleRepository;
import com.vn.spring.module_security.repository.IUserRepository;
import com.vn.spring.module_security.request.BearerToken;
import com.vn.spring.module_security.request.LoginRequest;
import com.vn.spring.module_security.request.RegisterRequest;
import com.vn.spring.module_security.security.JwtUtilities;
import com.vn.spring.module_security.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final IUserRepository iUserRepository;

    private final IRoleRepository iRoleRepository;

    private final PasswordEncoder passwordEncoder ;

    private final  JwtUtilities jwtUtilities;




    @Override
    public Role saveRole(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public User saverUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return iUserRepository.findByEmail(email);
    }


    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        if (iUserRepository.existsByEmail(registerRequest.getEmail())) {
//            return new ResponseEntity<>("Email đã tồn tại !", HttpStatus.SEE_OTHER);

            return ResponseEntity.status(409).body("Email đã tồn tại !");  // 409 conflict yêu cầu xung đột với tài nguyên hiện tại
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role role = iRoleRepository.findByRoleName(RoleName.USER);  // Mặc định user đăng kí là User
        user.setRoleList(Collections.singletonList(role));
        iUserRepository.save(user);
        String token = jwtUtilities.generateToken(registerRequest.getEmail(), Collections.singletonList(role.getRoleName().toString()));

//       return new ResponseEntity<>(new BearerToken(token, "Bearer"), HttpStatus.OK);
        return ResponseEntity.ok(new BearerToken(token, "Bearer"));
    }


    @Override
    public String authenticate(LoginRequest loginRequest) {
        Authentication authentication;
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = iUserRepository.findByEmail(authentication.getName()).orElseThrow(() ->
                new UsernameNotFoundException("Người dùng không tồn tại"));
        List<String > roleNames = new ArrayList<>();

        user.getRoleList().forEach(role -> roleNames.add(role.getRoleName().toString()));

        String token = jwtUtilities.generateToken(user.getUsername(), roleNames);
        return token;
    }


    @Override
    public List<User> getAllAdmin(Integer roleId) {
        Role role = iRoleRepository.findRoleById(roleId);
        List<User> userList = role.getUserList();
        return userList;
    }



    @Override
    public Role getRole(Integer roleId) {
        return iRoleRepository.findRoleById(roleId);
    }

    @Override
    public User findUserById(Long id) {
        return iUserRepository.findById(id).orElseThrow(()
                -> new UserAlreadyExistsException("User not found"));
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        iUserRepository.delete(user);
    }

    @Override
    public User getCurentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new JwtInvalidException("Jwt is not valid");
        }
        String email = authentication.getName();

        return iUserRepository.findByEmail(email).orElseThrow(()
                -> new UserAlreadyExistsException("User not found"));
    }


}
