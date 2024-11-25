package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Ensures the database is rolled back after each test
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Clear the database and add test data if necessary
        userRepository.deleteAll();
        userRepository.save(new User("testUser", "password", List.of()));
    }

    @Test
    void testRegisterUser_Success() throws Exception {
        String userJson = """
            {
                "username": "newUser",
                "password": "password"
            }
        """;

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newUser"));
    }

    @Test
    void testRegisterUser_Conflict() throws Exception {
        String userJson = """
            {
                "username": "testUser",
                "password": "password"
            }
        """;

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));
    }

    @Test
    void testLoginUser_Success() throws Exception {
        String loginJson = """
            {
                "username": "testUser",
                "password": "password"
            }
        """;

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    void testLoginUser_InvalidCredentials() throws Exception {
        String loginJson = """
            {
                "username": "testUser",
                "password": "wrongPassword"
            }
        """;

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid username or password"));    }

    @Test
    void testGetUserProfile_Success() throws Exception {
        mockMvc.perform(get("/api/users/profile/testUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.memberships").isEmpty());
    }

    @Test
    void testGetUserProfile_NotFound() throws Exception {
        mockMvc.perform(get("/api/users/profile/nonexistentUser"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }

    @Test
    void testUpdateUserProfile_Success() throws Exception {
        String updateJson = """
            {
                "username": "testUser",
                "memberships": ["Costco", "Toyota"]
            }
        """;

        mockMvc.perform(post("/api/users/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    void testUpdateUserProfile_ValidationError() throws Exception {
        String updateJson = """
            {
                "username": "",
                "memberships": ["Costco", "Toyota"]
            }
        """;

        mockMvc.perform(post("/api/users/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username cannot be null or empty"));
    }
}


