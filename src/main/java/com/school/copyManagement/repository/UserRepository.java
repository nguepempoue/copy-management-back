package com.school.copyManagement.repository;

import com.school.copyManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findUserByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByPhoneNumber(String phoneNumber);
}
