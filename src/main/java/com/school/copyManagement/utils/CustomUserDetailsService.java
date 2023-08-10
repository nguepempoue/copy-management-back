package com.school.copyManagement.utils;

import com.school.copyManagement.constant.Status;
import com.school.copyManagement.model.User;
import com.school.copyManagement.repository.UserRepository;
import com.school.copyManagement.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username or email : " + username));

        if(user.getStatus().equals(Status.DISABLED)){
            throw new UsernameNotFoundException("this user is not activted yet");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
}
