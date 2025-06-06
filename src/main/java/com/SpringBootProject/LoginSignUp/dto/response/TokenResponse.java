package com.SpringBootProject.LoginSignUp.dto.response;

import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {

    private String token;
    private String refreshToken;
    private String expirationTime;

}
