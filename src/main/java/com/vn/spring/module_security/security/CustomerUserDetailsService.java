package com.vn.spring.module_security.security;

import com.vn.spring.module_security.entity.User;
import com.vn.spring.module_security.exception.UserAlreadyExistsException;
import com.vn.spring.module_security.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {


    private final IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(email)
                .orElseThrow(() -> new UserAlreadyExistsException("User not found"));
        return user;
    }
}
