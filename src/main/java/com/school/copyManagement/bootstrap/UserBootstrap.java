package com.school.copyManagement.bootstrap;

import com.school.copyManagement.model.Role;
import com.school.copyManagement.model.User;
import com.school.copyManagement.service.RoleService;
import com.school.copyManagement.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserBootstrap {

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public UserBootstrap(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    // ACTUATOR
    public void seed() {
        if(userService.countAllUsers() == 0) {

            // USER & ROLE
            User userAdmin = new User();

            Role admin = roleService.findRoleByName("ADMIN");
            Role teatcher = roleService.findRoleByName("TEATCHER");
            Role student = roleService.findRoleByName("STUDENT");

            // SETTING VALUES ADMIN
            userAdmin.setFirstName("Admin");
            userAdmin.setLastName("Admin");
            userAdmin.setUserName("admin@gmail.com");
            userAdmin.setPassword(passwordEncoder.encode("123456"));
            userAdmin.setPhoneNumber("0210987654321");

            userAdmin.getRoles().add(admin);

            // SAVING VALUES ADMIN
            userService.saveUser(userAdmin);

            User userTeatcher = new User();

            // SETTING VALUES TEATCHER
            userTeatcher.setFirstName("Teatcher");
            userTeatcher.setLastName("Teatcher");
            userTeatcher.setUserName("teatcher@gmail.com");
            userTeatcher.setPassword(passwordEncoder.encode("123456"));
            userTeatcher.setPhoneNumber("1210987654321");

            userTeatcher.getRoles().add(teatcher);

            // SAVING VALUES TEATCHER
            userService.saveUser(userTeatcher);

            User userStudent = new User();

            // SETTING VALUES TEATCHER
            userStudent.setFirstName("Student");
            userStudent.setLastName("Student");
            userStudent.setUserName("student@gmail.com");
            userStudent.setPassword(passwordEncoder.encode("123456"));
            userStudent.setPhoneNumber("2210987654321");

            userStudent.getRoles().add(student);

            // SAVING VALUES TEATCHER
            userService.saveUser(userStudent);
        }
    }


}
