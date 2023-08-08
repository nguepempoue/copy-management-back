package com.school.copyManagement.service;

import com.school.copyManagement.dto.request.UserRequest;
import com.school.copyManagement.dto.response.ResponseHandler;
import com.school.copyManagement.model.User;
import com.school.copyManagement.repository.UserRepository;
import com.school.copyManagement.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GLOBAL USER CREATION
    public ResponseEntity<Object> createUser(UserRequest userRequest){

        // TEST EXISTENCE OF PROPERTIES

        if (userRepository.existsByUserName(userRequest.getEmail())) {
            return ResponseHandler.generateResponse("This email is already taken !", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            return ResponseHandler.generateResponse("This phone number is already taken !", HttpStatus.BAD_REQUEST, null);
        }


        // SAVING MEMBER AND RETURN RESPONSE
        try {

            // CREATE NEW MEMBER
            User user = new User();

            // TEST OF STRING VALUES
            checkStringValuesOfUserRequest(userRequest);

            // SETTING VALUES
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setUserName(userRequest.getEmail());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

            //SAVE AND RETURN
            return ResponseHandler.generateResponse("This user has been saved !", HttpStatus.CREATED, userRepository.save(user));
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    public User saveUser(User user) {

        // SAVING
        try {
            System.out.println("This member has been properly saved !");
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    // COUNT ALL
    public Long countAllUsers() {
        return userRepository.count();
    }


    /* **************************************** OTHER **************************************** */

    private void checkStringValuesOfUserRequest(UserRequest userRequest) throws Exception {

        // TESTS ON STRING VALUES
        Utils.checkStringValues(userRequest.getFirstName(), "First name"); // FIRSTNAME
        Utils.checkStringValues(userRequest.getLastName(), "Last name"); // LASTNAME
        Utils.checkStringValues(userRequest.getPhoneNumber(), "Phone number"); // PHONE NUMBER
        Utils.checkStringValues(userRequest.getEmail(), "Main email"); // MAIN EMAIL
        Utils.checkStringValues(userRequest.getPassword(), "Password"); // PASSWORD
    }
}


