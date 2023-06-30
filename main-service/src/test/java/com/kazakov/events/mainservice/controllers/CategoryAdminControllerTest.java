package com.kazakov.events.mainservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakov.events.mainservice.dto.*;
import com.kazakov.events.mainservice.exceptions.CategoryNotFoundException;
import com.kazakov.events.mainservice.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryAdminControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void givenCategory_whenCreateCategory_thenCreated() throws Exception {
        CategoryNewDto categoryNew = dummyCategory();

        mvc.perform(post("/admin/categories")
                        .content(mapper.writeValueAsString(categoryNew))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(categoryNew.getName()));
    }

    @Test
    void givenCategory_whenDeleteCategory_thenNotFound() throws Exception {
        CategoryNewDto categoryNew = dummyCategory();
        CategoryDto categoryCreated = categoryService.createCategory(categoryNew);

        mvc.perform(delete("/admin/categories/" + categoryCreated.getId())
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isNoContent());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findCategoryById(categoryCreated.getId()));
    }

    @Test
    void givenCategory_whenUpdateCategory_thenUpdated() throws Exception {
        CategoryNewDto categoryNew = dummyCategory();
        CategoryDto categoryCreated = categoryService.createCategory(categoryNew);
        CategoryNewDto categoryNew2 = dummyCategory();

        mvc.perform(patch("/admin/categories/" + categoryCreated.getId())
                        .content(mapper.writeValueAsString(categoryNew2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic(adminName, adminPassword)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryCreated.getId()))
                .andExpect(jsonPath("$.name").value(categoryNew2.getName()));
    }

}