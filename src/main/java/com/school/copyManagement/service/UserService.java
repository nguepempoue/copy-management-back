package com.school.copyManagement.service;

import com.school.copyManagement.constant.Status;
import com.school.copyManagement.dto.request.UserRequest;
import com.school.copyManagement.dto.response.ResponseHandler;
import com.school.copyManagement.model.Role;
import com.school.copyManagement.model.User;
import com.school.copyManagement.repository.RoleRepository;
import com.school.copyManagement.repository.UserRepository;
import com.school.copyManagement.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GLOBAL USER CREATION
    public ResponseEntity<Object> createUser(UserRequest userRequest){

        Optional <Role> role = roleRepository.findById(2L);

        if (!role.isPresent()) {
            return ResponseHandler.generateResponse("This role doesn't exist !", HttpStatus.BAD_REQUEST, null);
        }

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
            user.getRoles().add(role.get());
            user.setStatus(Status.DISABLED);

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

    public ResponseEntity<Object> createAdminOrStudent(UserRequest userRequest, Long idRole){

        Optional <Role> role = roleRepository.findById(idRole);

        // TEST EXISTENCE OF PROPERTIES

        if (!role.isPresent()) {
            return ResponseHandler.generateResponse("This role doesn't exist !", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.existsByUserName(userRequest.getEmail())) {
            return ResponseHandler.generateResponse("This email is already taken !", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            return ResponseHandler.generateResponse("This phone number is already taken !", HttpStatus.BAD_REQUEST, null);
        }


        // SAVING USER AND RETURN RESPONSE
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
            user.getRoles().add(role.get());

            //SAVE AND RETURN
            return ResponseHandler.generateResponse("This user has been saved !", HttpStatus.CREATED, userRepository.save(user));
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    public ResponseEntity<Object> findAll() {
        try {
            // GETTING ALL ROLES
            List<User> users = this.userRepository.findAll();
            if(users.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Users list", users);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }

    public ResponseEntity<Object> findUserAdmin() {

        List<User> users = userRepository.findByRoleId(1L);

        try {
            if(users.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Admin list", users);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
    public ResponseEntity<Object> findUserTeatcher() {

        List<User> users = userRepository.findByRoleId(2L);

        try {
            if(users.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Teatcher list", users);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> findUserStudent() {

        List<User> users = userRepository.findByRoleId(3L);

        try {
            if(users.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Student list", users);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public void changeUserStatus(Long idUser, Status status){
        Optional<User> user = userRepository.findById(idUser);
        try {
            if(!user.isPresent()) {
                System.out.println("User not found !");
            }else{

                user.get().setStatus(status);
                userRepository.save(user.get());

                System.out.println("user2:: " + userRepository.save(user.get()));
            }

        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public ResponseEntity<Object> findUserByEmail(String email) {
        // GET User
        Optional<User> user = userRepository.findUserByUserName(email);
        try {
            if(!user.isPresent()) {
                System.out.println("User not found !");
                return null;
            }
            return ResponseHandler.generateOkResponse("User", user.get());
        }
        catch (Exception e) {
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


