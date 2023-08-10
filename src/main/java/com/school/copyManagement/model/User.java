package com.school.copyManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.copyManagement.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String phoneNumber;

    private Status status;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "roles_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

}
