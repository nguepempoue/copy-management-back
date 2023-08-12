package com.school.copyManagement.controller;

import com.school.copyManagement.constant.Status;
import com.school.copyManagement.dto.request.UserRequest;
import com.school.copyManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE ADMIN OR STUDENT
    @PostMapping("/create-user/role/{idRole}")
    public ResponseEntity<Object> createAdminOrStudent (@RequestBody UserRequest userRequest, @PathVariable("idRole") Long idRole) {
        return userService.createAdminOrStudent(userRequest, idRole);
    }

    // FIND ALL USERS
    @GetMapping("/all")
    public ResponseEntity<Object> findAllAreas() {
        return userService.findAll();
    }

    // FIND ALL ADMIN USERS
    @GetMapping("/admins")
    public ResponseEntity<Object> findUserAdmin() {
        return userService.findUserAdmin();
    }

    // FIND ALL TEATCHER USERS
    @GetMapping("/teatcher")
    public ResponseEntity<Object> findUserTeatcher() {
        return userService.findUserTeatcher();
    }

    // FIND ALL STUDENT USERS
    @GetMapping("/students")
    public ResponseEntity<Object> findUserStudent() {
        return userService.findUserStudent();
    }

    // FIND USER BY EMAIL
    @GetMapping("/email")
    public ResponseEntity<Object> findUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    // FIND ALL STUDENT USERS
    @GetMapping("/status")
    public void changeUserStatus(@RequestParam Long idUser, @RequestParam Status status) {
         userService.changeUserStatus(idUser, status);
    }


}
