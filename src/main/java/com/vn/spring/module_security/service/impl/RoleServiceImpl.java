package com.vn.spring.module_security.service.impl;

import com.vn.spring.module_security.entity.Role;
import com.vn.spring.module_security.repository.IRoleRepository;
import com.vn.spring.module_security.repository.IUserRepository;
import com.vn.spring.module_security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final IRoleRepository iRoleRepository;

    @Override
    public Role findRoleById(Integer id) {

        return iRoleRepository.findRoleById(id);
    }
}
