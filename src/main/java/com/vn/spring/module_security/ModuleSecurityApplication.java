package com.vn.spring.module_security;

import com.vn.spring.module_security.entity.Role;
import com.vn.spring.module_security.entity.RoleName;
import com.vn.spring.module_security.entity.User;
import com.vn.spring.module_security.repository.IRoleRepository;
import com.vn.spring.module_security.repository.IUserRepository;
import com.vn.spring.module_security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ModuleSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleSecurityApplication.class, args);
	}


//	@Bean
//	CommandLineRunner run (UserService iUserService , IRoleRepository iRoleRepository , IUserRepository iUserRepository , PasswordEncoder passwordEncoder)
//	{return  args ->
//	{   iUserService.saveRole(new Role(RoleName.USER));
//		iUserService.saveRole(new Role(RoleName.ADMIN));
//		iUserService.saveRole(new Role(RoleName.SUPERADMIN));
//		iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("adminPassword"), new ArrayList<>()));
//		iUserService.saverUser(new User("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new ArrayList<>()));
//
//
//		Role role = iRoleRepository.findByRoleName(RoleName.ADMIN);
//		User user = iUserRepository.findByEmail("admin@gmail.com").orElse(null);
//		user.getRoleList().add(role);
//		iUserService.saverUser(user);
//
//		User userr = iUserRepository.findByEmail("superadminadmin@gmail.com").orElse(null);
//		Role rolee = iRoleRepository.findByRoleName(RoleName.SUPERADMIN);
//		userr.getRoleList().add(rolee);
//		iUserService.saverUser(userr);
//	} ;
//	}

}
