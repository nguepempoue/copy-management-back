package com.school.copyManagement.service;

import com.school.copyManagement.dto.response.ResponseHandler;
import com.school.copyManagement.model.Role;
import com.school.copyManagement.repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // CREATE ROLE
    public Role createRole(Role role) {

        // NEW ROLE
        Role r = new Role();

        try {
            // SETTING VALUES
            r.setName(role.getName());
            r = roleRepository.save(r);
            return r;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // GETTING ALL ROLES
    public List<Role> findAllRoles() {

        try {
            // GETTING ALL ROLES
            List<Role> roles = findAllRoles();
            if(roles.isEmpty())
            {
                return null;
            }
            return roles;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> findAll() {
        try {
            // GETTING ALL ROLES
            List<Role> roles = this.roleRepository.findAll();
            if(roles.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Roles list", roles);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ROLE BY NAME
    public Role findRoleByName(String name) {

        // GET ROLE
        Optional<Role> role = roleRepository.findRoleByName(name);
        try {
            if(!role.isPresent()) {
                System.out.println("Role not found !");
                return null;
            }
            return role.get();
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // FIND ROLE BY ID
    public Role findRoleById(Long id) {

        // GET ROLE
        Optional<Role> role = roleRepository.findById(id);
        try {
            if(!role.isPresent()) {
                System.out.println("Role not found !");
                return null;
            }
            return role.get();
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    // COUNT ALL ROLES
    public Long countAllRoles() {
        return roleRepository.count();
    }
}
