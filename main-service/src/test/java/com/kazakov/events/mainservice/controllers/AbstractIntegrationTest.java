package com.kazakov.events.mainservice.controllers;

import com.kazakov.events.mainservice.dto.*;
import com.kazakov.events.mainservice.model.Location;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public abstract class AbstractIntegrationTest {
    @Value("${default.admin.name}")
    protected String adminName;
    @Value("${default.admin.password}")
    protected String adminPassword;

    protected UserShortDto dummyUser() {
        return UserShortDto
                .builder()
                .name(randomAlphabetic(8))
                .email(randomAlphabetic(8)+"@gmail.com")
                .password(randomAlphabetic(8)).build();
    }

    protected CategoryNewDto dummyCategory() {
        return CategoryNewDto
                .builder()
                .name(randomAlphabetic(8))
                .build();
    }
    protected EventNewDto dummyEvent(Long categoryId) {
        return EventNewDto
                .builder()
                .annotation(randomAlphabetic(30))
                .category(categoryId)
                .description(randomAlphabetic(30))
                .eventDate(LocalDateTime.now().plusDays(3))
                .location(new Location(
                        ThreadLocalRandom.current().nextDouble(-90.0, 90.0),
                        ThreadLocalRandom.current().nextDouble(-180.0, 180.0)))
                .title(randomAlphabetic(10))
                .build();
    }

    protected EventUpdateDto dummyPublishedEvent(Long categoryId) {
        return EventUpdateDto
                .builder()
                .annotation(randomAlphabetic(30))
                .category(categoryId)
                .description(randomAlphabetic(30))
                .eventDate(LocalDateTime.now().plusDays(3))
                .location(new Location(
                        ThreadLocalRandom.current().nextDouble(-90.0, 90.0),
                        ThreadLocalRandom.current().nextDouble(-180.0, 180.0)))
                .title(randomAlphabetic(10))
                .stateAction(EventAction.PUBLISH_EVENT)
                .requestModeration(true)
                .build();
    }

    protected CommentNewDto dummyComment(Long eventId) {
        return CommentNewDto
                .builder()
                .event(eventId)
                .text(randomAlphabetic(30))
                .build();
    }
}
