package com.vn.spring.module_security.repository;

import com.vn.spring.module_security.entity.Role;
import com.vn.spring.module_security.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName user);

    Role findRoleById(Integer id);
}
