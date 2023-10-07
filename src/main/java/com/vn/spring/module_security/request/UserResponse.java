package com.vn.spring.module_security.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    String firstName ;

    String lastName ;

    String email;

    String password ;

    List<Integer> roleId;

}
