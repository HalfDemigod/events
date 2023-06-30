package com.kazakov.events.mainservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakov.events.mainservice.dto.*;
import com.kazakov.events.mainservice.model.EventState;
import com.kazakov.events.mainservice.model.RequestStatus;
import com.kazakov.events.mainservice.services.CategoryService;
import com.kazakov.events.mainservice.services.EventService;
import com.kazakov.events.mainservice.services.RequestService;
import com.kazakov.events.mainservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EventPrivateControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void givenUserAndEvent_whenGetAllUserEvents_thenFound() throws Exception {
        UserShortDto userNew = dummyUser();
        UserDto userCreated = userService.createUser(userNew);

        EventFullDto eventNew = eventService.createEvent(
                dummyEvent(categoryService.createCategory(dummyCategory()).getId()),
                userCreated.getId());

        mvc.perform(get(String.format("/users/%d/events", userCreated.getId()))
                        .with(httpBasic(userNew.getName(), userNew.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[*].id", hasItem(is(eventNew.getId().intValue()))));
    }

    @Test
    public void givenEvent_whenCreateEvent_thenCreated() throws Exception {
        UserShortDto userNew = dummyUser();
        UserDto userCreated = userService.createUser(userNew);
        EventNewDto eventNew = dummyEvent(categoryService.createCategory(dummyCategory()).getId());

        mvc.perform(post(String.format("/users/%d/events", userCreated.getId()))
                        .content(mapper.writeValueAsString(eventNew))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(userNew.getName(), userNew.getPassword())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.annotation").value(eventNew.getAnnotation()))
                .andExpect(jsonPath("$.initiator.id").value(userCreated.getId()))
                .andExpect(jsonPath("$.state").value(EventState.PENDING.name()));
    }

    @Test
    public void givenEvent_whenGetEventById_thenFound() throws Exception {
        UserShortDto userNew = dummyUser();
        UserDto userCreated = userService.createUser(userNew);
        EventNewDto eventNew = dummyEvent(categoryService.createCategory(dummyCategory()).getId());
        EventFullDto eventCreated = eventService.createEvent(eventNew, userCreated.getId());

        mvc.perform(get(String.format("/users/%d/events/%d", userCreated.getId(), eventCreated.getId()))
                        .with(httpBasic(userNew.getName(), userNew.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(eventCreated.getId()));
    }

    @Test
    public void givenEvent_whenUpdateEvent_thenUpdated() throws Exception {
        UserShortDto userNew = dummyUser();
        UserDto userCreated = userService.createUser(userNew);
        CategoryDto categoryDto = categoryService.createCategory(dummyCategory());
        EventNewDto eventNew = dummyEvent(categoryDto.getId());
        EventFullDto eventCreated = eventService.createEvent(eventNew, userCreated.getId());
        EventNewDto eventUpdated = dummyEvent(categoryDto.getId());

        mvc.perform(patch(String.format("/users/%d/events/%d", userCreated.getId(), eventCreated.getId()))
                        .content(mapper.writeValueAsString(eventUpdated))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(userNew.getName(), userNew.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(eventCreated.getId()))
                .andExpect(jsonPath("$.annotation").value(eventUpdated.getAnnotation()));
    }

    @Test
    public void givenEventAndRequest_whenGetAllEventRequests_thenFound() throws Exception {
        UserShortDto userNew = dummyUser();
        UserDto userCreated = userService.createUser(userNew);
        UserShortDto userNew2 = dummyUser();
        UserDto userCreated2 = userService.createUser(userNew2);
        CategoryDto categoryDto = categoryService.createCategory(dummyCategory());
        EventNewDto eventNew = dummyEvent(categoryDto.getId());
        EventFullDto eventCreated = eventService.createEvent(eventNew, userCreated.getId());
        eventService.updateEvent(dummyPublishedEvent(categoryDto.getId()), userCreated.getId(), eventCreated.getId());
        RequestDto request = requestService.createRequest(userCreated2.getId(), eventCreated.getId());

        mvc.perform(get(String.format("/users/%d/events/%d/requests", userCreated.getId(), eventCreated.getId()))
                        .with(httpBasic(userNew.getName(), userNew.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[*].id", hasItem(is(request.getId().intValue()))))
                .andExpect(jsonPath("$.[*].event", hasItem(is(eventCreated.getId().intValue()))));
    }

    @Test
    public void givenEventAndRequest_whenUpdateRequests_thenUpdated() throws Exception {
        UserShortDto userNew = dummyUser();
        UserDto userCreated = userService.createUser(userNew);
        UserShortDto userNew2 = dummyUser();
        UserDto userCreated2 = userService.createUser(userNew2);
        CategoryDto categoryDto = categoryService.createCategory(dummyCategory());
        EventNewDto eventNew = dummyEvent(categoryDto.getId());
        EventFullDto eventCreated = eventService.createEvent(eventNew, userCreated.getId());
        eventService.updateEvent(dummyPublishedEvent(categoryDto.getId()), userCreated.getId(), eventCreated.getId());
        RequestDto request = requestService.createRequest(userCreated2.getId(), eventCreated.getId());
        RequestUpdateDto requestUpdateDto = RequestUpdateDto
                .builder()
                .requestIds(List.of(request.getId()))
                .status(RequestStatus.CONFIRMED)
                .build();

        mvc.perform(patch(String.format("/users/%d/events/%d/requests", userCreated.getId(), eventCreated.getId()))
                        .content(mapper.writeValueAsString(requestUpdateDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(userNew.getName(), userNew.getPassword())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.confirmedRequests", hasSize(1)))
                .andExpect(jsonPath("$.confirmedRequests.[*].id", hasItem(is(request.getId().intValue()))));
    }


}