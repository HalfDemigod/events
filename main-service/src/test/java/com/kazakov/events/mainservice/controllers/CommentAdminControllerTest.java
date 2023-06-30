package com.kazakov.events.mainservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakov.events.mainservice.dto.*;
import com.kazakov.events.mainservice.exceptions.CommentNotFoundException;
import com.kazakov.events.mainservice.services.CategoryService;
import com.kazakov.events.mainservice.services.CommentService;
import com.kazakov.events.mainservice.services.EventService;
import com.kazakov.events.mainservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CommentAdminControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void givenComment_whenDeleteComment_thenNotFound() throws Exception {
        UserDto userCreated = userService.createUser(dummyUser());
        CategoryDto categoryNew = categoryService.createCategory(dummyCategory());
        EventFullDto eventCreated = eventService.createEvent(dummyEvent(categoryNew.getId()), userCreated.getId());
        eventService.updateEvent(dummyPublishedEvent(categoryNew.getId()), userCreated.getId(), eventCreated.getId());
        CommentDto commentCreated = commentService.createComment(dummyComment(eventCreated.getId()), userCreated.getId());

        mvc.perform(delete("/admin/comments/" + commentCreated.getId())
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isNoContent());

        assertThrows(CommentNotFoundException.class, () -> commentService.findComment(commentCreated.getId()));
    }

    @Test
    void updateComment() throws Exception {
        UserDto userCreated = userService.createUser(dummyUser());
        CategoryDto categoryNew = categoryService.createCategory(dummyCategory());
        EventFullDto eventCreated = eventService.createEvent(dummyEvent(categoryNew.getId()), userCreated.getId());
        eventService.updateEvent(dummyPublishedEvent(categoryNew.getId()), userCreated.getId(), eventCreated.getId());
        CommentDto commentCreated = commentService.createComment(dummyComment(eventCreated.getId()), userCreated.getId());
        CommentNewDto commentUpdated = dummyComment(eventCreated.getId());

        mvc.perform(patch("/admin/comments/" + commentCreated.getId())
                        .content(mapper.writeValueAsString(commentUpdated))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentCreated.getId()))
                .andExpect(jsonPath("$.text").value(commentUpdated.getText()));
    }

}