package com.school.copyManagement.bootstrap;

import com.school.copyManagement.model.Role;
import com.school.copyManagement.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleBootstrap {

    private final RoleService roleService;

    public RoleBootstrap(RoleService roleService) {
        this.roleService = roleService;
    }


    // ROLES
    public void seed() {
        if(roleService.countAllRoles() == 0)
        {
            roleService.createRole(new Role("ADMIN"));
            roleService.createRole(new Role("TEATCHER"));
            roleService.createRole(new Role("STUDENT"));
        }
    }
}
