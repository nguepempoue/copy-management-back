package com.school.copyManagement.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainBootstrap implements CommandLineRunner {

    private final RoleBootstrap roleBootstrap;

    private final UserBootstrap userBootstrap;

    public MainBootstrap(RoleBootstrap roleBootstrap, UserBootstrap userBootstrap) {
        this.roleBootstrap = roleBootstrap;
        this.userBootstrap = userBootstrap;
    }

    @Override
    public void run(String... args) throws Exception {
        roleBootstrap.seed();
        userBootstrap.seed();
    }
}
