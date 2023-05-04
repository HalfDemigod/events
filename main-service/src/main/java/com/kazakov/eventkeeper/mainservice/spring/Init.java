package com.kazakov.eventkeeper.mainservice.spring;

import com.kazakov.eventkeeper.mainservice.dto.UserShortDto;
import com.kazakov.eventkeeper.mainservice.security.Roles;
import com.kazakov.eventkeeper.mainservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) {
        if (userService.findAllUsersByRole(Roles.ROLE_ADMIN).size() == 0) {
            UserShortDto admin = UserShortDto
                    .builder()
                    .name("admin")
                    .password("admin")
                    .admin(true)
                    .build();
            userService.createUser(admin);
        }
    }
}
