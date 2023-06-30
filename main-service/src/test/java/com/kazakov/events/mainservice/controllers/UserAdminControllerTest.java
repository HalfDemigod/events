package com.kazakov.events.mainservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakov.events.mainservice.dto.UserDto;
import com.kazakov.events.mainservice.dto.UserShortDto;
import com.kazakov.events.mainservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserAdminControllerTest {
    @Value("${default.admin.name}")
    private String adminName;
    @Value("${default.admin.password}")
    private String adminPassword;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenGetAllUsers_thenOk() throws Exception {
        int countBefore = userService.findAllUsers(null, null, null).size();

        userService.createUser(dummyUser());

        mvc.perform(get("/admin/users")
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(countBefore + 1)));
    }

    @Test
    public void whenGetSpecificUsers_thenOk() throws Exception {
        UserDto user1 = userService.createUser(dummyUser());
        UserDto user2 = userService.createUser(dummyUser());

        mvc.perform(get("/admin/users")
                        .param("ids", user1.getId().toString())
                        .param("ids", user2.getId().toString())
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id", hasItem(is(user1.getId().intValue()))))
                .andExpect(jsonPath("$.[*].id", hasItem(is(user1.getId().intValue()))));
    }

    @Test
    public void givenUser_whenCreateUser_thenCreated() throws Exception {
        UserShortDto user = dummyUser();
        mvc.perform(post("/admin/users")
                        .content(mapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void givenUser_whenDeleteUser_thenNotFound() throws Exception {
        UserDto userDto = userService.createUser(dummyUser());

        mvc.perform(delete("/admin/users/" + userDto.getId())
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isNoContent());

        assertTrue(userService.findAllUsers(null, null, null)
                .stream()
                .filter(user -> user.getId().equals(userDto.getId()))
                .findAny()
                .isEmpty());
    }

    private UserShortDto dummyUser() {
        return UserShortDto
                .builder()
                .name(randomAlphabetic(8))
                .email(randomAlphabetic(8)+"@gmail.com")
                .password(randomAlphabetic(8)).build();
    }

}