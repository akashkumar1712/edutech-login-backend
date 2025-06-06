package com.SpringBootProject.LoginSignUp.dto.response;

import com.SpringBootProject.LoginSignUp.enums.Role;
import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    private Role role;
    private String token;
    private String refreshToken;
    private String expirationTime;

}
