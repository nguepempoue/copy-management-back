package com.school.copyManagement.controller;

import com.school.copyManagement.dto.request.LoginRequest;
import com.school.copyManagement.dto.request.UserRequest;
import com.school.copyManagement.dto.response.ResponseHandler;
import com.school.copyManagement.service.UserService;
import com.school.copyManagement.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return ResponseHandler.generateResponse("Authentication token", HttpStatus.OK, jwtUtil.generateToken(loginRequest.getEmail()));
        }
        catch (Exception e) {
            throw new Exception(e);
        }

    }

    // CREATE MEMBER
    @PostMapping("/register")
    public ResponseEntity<Object> createTeatcher (@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

}
