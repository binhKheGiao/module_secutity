package com.vn.spring.module_security.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BearerToken {  // Class chứa token và loại token

    private String accessToken ;
    private String tokenType ;

}
