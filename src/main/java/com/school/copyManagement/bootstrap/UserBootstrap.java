package com.school.copyManagement.bootstrap;

import com.school.copyManagement.constant.Status;
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
            userAdmin.setStatus(Status.ACTIVATED);

            userAdmin.getRoles().add(admin);

            // SAVING VALUES ADMIN
            userService.saveUser(userAdmin);

            User userTeatcher = new User();

            // SETTING VALUES TEATCHER
            userTeatcher.setFirstName("Teatcher1");
            userTeatcher.setLastName("Teatcher1");
            userTeatcher.setUserName("teatcher1@gmail.com");
            userTeatcher.setPassword(passwordEncoder.encode("123456"));
            userTeatcher.setPhoneNumber("1210987654321");
            userTeatcher.setStatus(Status.ACTIVATED);

            userTeatcher.getRoles().add(teatcher);

            // SAVING VALUES TEATCHER
            userService.saveUser(userTeatcher);

            User userTeatcher2 = new User();

            // SETTING VALUES TEATCHER
            userTeatcher2.setFirstName("Teatcher2");
            userTeatcher2.setLastName("Teatcher2");
            userTeatcher2.setUserName("teatcher2@gmail.com");
            userTeatcher2.setPassword(passwordEncoder.encode("123456"));
            userTeatcher2.setPhoneNumber("1210987654321");
            userTeatcher2.setStatus(Status.ACTIVATED);

            userTeatcher2.getRoles().add(teatcher);

            // SAVING VALUES TEATCHER
            userService.saveUser(userTeatcher2);

            User userStudent1 = new User();

            // SETTING VALUES TEATCHER
            userStudent1.setFirstName("Student1");
            userStudent1.setLastName("Student1");
            userStudent1.setUserName("student1@gmail.com");
            userStudent1.setPassword(passwordEncoder.encode("123456"));
            userStudent1.setPhoneNumber("2210987654321");
            userStudent1.setStatus(Status.ACTIVATED);

            userStudent1.getRoles().add(student);
            userService.saveUser(userStudent1);

            User userStudent2 = new User();

            // SETTING VALUES TEATCHER
            userStudent2.setFirstName("Student2");
            userStudent2.setLastName("Student2");
            userStudent2.setUserName("student2@gmail.com");
            userStudent2.setPassword(passwordEncoder.encode("123456"));
            userStudent2.setPhoneNumber("2210987654321");
            userStudent2.setStatus(Status.ACTIVATED);


            userStudent2.getRoles().add(student);

            // SAVING VALUES TEATCHER
            userService.saveUser(userStudent2);
        }
    }


}
