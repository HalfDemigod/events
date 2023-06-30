package com.kazakov.events.mainservice.spring;

import com.kazakov.events.mainservice.dto.UserShortDto;
import com.kazakov.events.mainservice.security.Roles;
import com.kazakov.events.mainservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {
    @Value("${default.admin.name}")
    private String adminName;
    @Value("${default.admin.password}")
    private String adminPassword;

    private final UserService userService;

    @Override
    public void run(String... args) {
        if (userService.findAllUsersByRole(Roles.ROLE_ADMIN).size() == 0) {
            UserShortDto admin = UserShortDto
                    .builder()
                    .name(adminName)
                    .password(adminPassword)
                    .admin(true)
                    .build();
            userService.createUser(admin);
        }
    }
}
