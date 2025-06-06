package com.SpringBootProject.LoginSignUp.dto.response;

import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDtoListResponse {
    private List<UserDTO> usersDtoList;
}
