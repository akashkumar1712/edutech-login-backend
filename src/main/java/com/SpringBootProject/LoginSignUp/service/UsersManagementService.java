package com.SpringBootProject.LoginSignUp.service;

import com.SpringBootProject.LoginSignUp.dto.request.LoginRequest;
import com.SpringBootProject.LoginSignUp.dto.request.RegistrationRequest;
import com.SpringBootProject.LoginSignUp.dto.response.TokenResponse;
import com.SpringBootProject.LoginSignUp.dto.response.LoginResponse;
import com.SpringBootProject.LoginSignUp.dto.response.UserDTO;
import com.SpringBootProject.LoginSignUp.dto.response.UserDtoListResponse;
import com.SpringBootProject.LoginSignUp.entity.User;
import com.SpringBootProject.LoginSignUp.repository.UsersRepo;
import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersManagementService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ApiResponse<String> register(RegistrationRequest registrationRequest){
        try {
            User ourUser = new User();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setCity(registrationRequest.getCity());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setName(registrationRequest.getName());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User userResult = usersRepo.save(ourUser);
            if (userResult.getId()>0) {
                return ApiResponse.success( "User Registered Successfully");
            }

        }catch (Exception e){
            //log.error(String.format("Exception While saving User : %s", e));
            return ApiResponse.internalError("Exception While saving User to DB");
        }
        return ApiResponse.internalError("Exception While saving User to DB2");

    }


    public ApiResponse<LoginResponse> login(LoginRequest loginRequest){
        LoginResponse response = new LoginResponse();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            return ApiResponse.success(response, "User Logged in !!!");
        }catch (Exception e){
            //log.error(String.format("Exception While User Login : %s", e));
            return ApiResponse.internalError("Exception While User Login");
        }
    }





    public ApiResponse<TokenResponse> refreshToken(String token){
        TokenResponse response  = new TokenResponse();
        try{
            String ourEmail = jwtUtils.extractUsername(token);
            User users = usersRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(token, users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setToken(jwt);
                response.setRefreshToken(token);
                response.setExpirationTime("24Hr");
            }
            return ApiResponse.success(response, "Successfully Refreshed Token");

        }catch (Exception e){
            //log.error(String.format("Token refresh failed : %s", e));
            return ApiResponse.internalError("Token refresh failed :" + e.getMessage());
        }
    }


    public ApiResponse<UserDtoListResponse> getAllUsers() {
        List<User> users = usersRepo.findAll();
        if (!users.isEmpty()) {
            List<UserDTO> userDTOList = users.stream()
                    .map(user -> new UserDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getCity(),
                            user.getCredits(),
                            user.getRole()
                    ))
                    .toList();
            UserDtoListResponse userDtoListResponse = new UserDtoListResponse();
            userDtoListResponse.setUsersDtoList(userDTOList);
            return ApiResponse.success(userDtoListResponse, "Users found");
        }
        return ApiResponse.badRequest("No Users Available");
    }


    public ApiResponse<UserDTO> getUsersById(Integer id) {
        try {
            User user = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getCity(),
                    user.getCredits(),
                    user.getRole()
            );
            return ApiResponse.success(userDTO, "User found");

        } catch (Exception e) {
            //log.error(String.format("User not found : %s", e));
            return ApiResponse.internalError("User not found");
        }
    }


    public ApiResponse<String> deleteUser(Integer userId) {
        try {
            Optional<User> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                usersRepo.deleteById(userId);
                return ApiResponse.success("User deleted successfully");
            } else {
                return ApiResponse.badRequest("User not found for deletion");
            }
        } catch (Exception e) {
            return ApiResponse.internalError("Exception while deleting user: " + e.getMessage());
        }
    }

    public ApiResponse<UserDTO> updateUser(Integer userId, UserDTO updatedUser) {
        try {
            Optional<User> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User user = usersRepo.save(existingUser);
                UserDTO userDTO = new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getCity(),
                        user.getCredits(),
                        user.getRole()
                );
                return ApiResponse.success(userDTO, "User updated successfully");
            } else {
                return ApiResponse.badRequest("User not found for update");
            }
        } catch (Exception e) {
            //log.error(String.format("Exception while updating user: %s", e));
            return ApiResponse.internalError("Exception while updating user: " + e.getMessage());
        }
    }


    public ApiResponse<UserDTO> getMyInfo(String email) {
        try {
            Optional<User> userOptional = usersRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDTO userDTO = new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getCity(),
                        user.getCredits(),
                        user.getRole()
                );
                return ApiResponse.success(userDTO, "User info found");
            } else {
                return ApiResponse.badRequest("User not found");
            }

        }catch (Exception e){
            return ApiResponse.internalError("Exception while getting user info: " + e.getMessage());
        }
    }
}
