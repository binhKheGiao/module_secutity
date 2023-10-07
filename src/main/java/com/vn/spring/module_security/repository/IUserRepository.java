package com.vn.spring.module_security.repository;

import com.vn.spring.module_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT user.* FROM user_role JOIN user ON user_role.user_id = user.id " +
            "             WHERE user_role.role_id = :roleId ", nativeQuery = true)
    List<User> findUserByRole(@Param("roleId") Integer roleId);

}
