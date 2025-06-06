package com.SpringBootProject.LoginSignUp.dto.request;

import com.SpringBootProject.LoginSignUp.dto.response.AnswerDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class AnswerRequest {
    private String emailId;
    private List<AnswerDTO> answers;
}
