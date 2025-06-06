package com.SpringBootProject.LoginSignUp.controller;

import com.SpringBootProject.LoginSignUp.dto.request.RegistrationRequest;
import com.SpringBootProject.LoginSignUp.service.UsersManagementService;
import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class SubjectController {

//    @GetMapping("/subject")
//    public ApiResponse<String> register(@RequestBody RegistrationRequest reg){
//        return usersManagementService.register(reg);
//    }