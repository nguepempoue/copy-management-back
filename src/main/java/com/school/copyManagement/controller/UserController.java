package com.school.copyManagement.controller;

import com.school.copyManagement.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // CREATE ADMIN OR STUDENT
//    @PostMapping("/register")
//    public ResponseEntity<Object> createAdminOrStudent (@RequestBody UserRequest userRequest) {
//        return userService.createUser(userRequest);
//    }
}
