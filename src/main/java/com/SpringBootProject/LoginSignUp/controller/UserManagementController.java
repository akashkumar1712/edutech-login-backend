package com.SpringBootProject.LoginSignUp.controller;


import com.SpringBootProject.LoginSignUp.dto.request.LoginRequest;
import com.SpringBootProject.LoginSignUp.dto.request.RegistrationRequest;
import com.SpringBootProject.LoginSignUp.dto.response.LoginResponse;
import com.SpringBootProject.LoginSignUp.dto.response.TokenResponse;
import com.SpringBootProject.LoginSignUp.dto.response.UserDTO;
import com.SpringBootProject.LoginSignUp.dto.response.UserDtoListResponse;
import com.SpringBootProject.LoginSignUp.entity.User;
import com.SpringBootProject.LoginSignUp.service.UsersManagementService;
import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@Validated
public class UserManagementController {
    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ApiResponse<String> register(@Valid @RequestBody RegistrationRequest reg){
        log.info("Started registering......");
        return usersManagementService.register(reg);
    }

    @PostMapping("/auth/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest req){
        log.info("Started login details..---");
        return usersManagementService.login(req);
    }

    @PostMapping("/auth/refresh")
    public ApiResponse<TokenResponse> refreshToken(@RequestBody String req){
        return usersManagementService.refreshToken(req);
    }

    @GetMapping("/admin/get-all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserDtoListResponse> getAllUsers(){
        return usersManagementService.getAllUsers();

    }

    @GetMapping("/admin/get-users/{userId}")
    public ApiResponse<UserDTO> getUSerByID(@PathVariable Integer userId) {
        return usersManagementService.getUsersById(userId);

    }

    @PutMapping("/admin/update/{userId}")
    public ApiResponse<UserDTO> updateUser(@PathVariable Integer userId, @RequestBody UserDTO user){
        return usersManagementService.updateUser(userId, user);
    }

    @GetMapping("/adminuser/get-profile")
    public ApiResponse<UserDTO> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        String email = authentication.getName();
        return usersManagementService.getMyInfo(email);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ApiResponse<String> deleteUSer(@PathVariable Integer userId){
        return usersManagementService.deleteUser(userId);
    }


}