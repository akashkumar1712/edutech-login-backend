package com.SpringBootProject.LoginSignUp.dto.response;

import com.SpringBootProject.LoginSignUp.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private int credits;
    private Role role;

}
