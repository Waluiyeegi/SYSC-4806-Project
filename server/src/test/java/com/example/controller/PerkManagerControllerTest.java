package com.example.controller;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.model.Membership;
import com.example.model.Perk;
import com.example.repository.PerkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PerkManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerkRepository perkRepository;

    @Autowired
    private ObjectMapper objectMapper; // For JSON serialization

    private Perk mockPerk;

    @BeforeEach
    void setUp() {
        perkRepository.deleteAll(); // Clean repository before each test

        // Create and save a sample perk
        mockPerk = new Perk();
        mockPerk.setName("Free Basketball");
        mockPerk.setCode("Bball");
        mockPerk.setProduct("Product A");
        mockPerk.setMembership(new Membership("Gold"));
        mockPerk.setGeographicArea("USA");
        mockPerk.setExpiryDate(LocalDate.of(2025, 8, 15));
        mockPerk.setUpvotes(0);
        mockPerk.setDownvotes(0);
        perkRepository.save(mockPerk);
    }

    @Test
    void testGetPerks() throws Exception {
        mockMvc.perform(get("/api/perks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(mockPerk.getName())))
                .andExpect(jsonPath("$[0].code", is(mockPerk.getCode())))
                .andExpect(jsonPath("$[0].membership", is(mockPerk.getMembership())));
    }

    @Test
    void testAddNewPerk() throws Exception {
        Perk newPerk = new Perk();
        newPerk.setName("Discount on Disk");
        newPerk.setCode("UltimateFrisbee");
        newPerk.setProduct("Product B");
        newPerk.setMembership(new Membership("Silver"));
        newPerk.setGeographicArea("Canada");
        newPerk.setExpiryDate(LocalDate.of(2025, 6, 30));

        mockMvc.perform(post("/api/perks/addNewPerk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPerk)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newPerk.getName())))
                .andExpect(jsonPath("$.code", is(newPerk.getCode())))
                .andExpect(jsonPath("$.membership", is(newPerk.getMembership())));
    }

    @Test
    void testUpvotePerk() throws Exception {
        mockMvc.perform(post("/api/perks/{id}/upvote", mockPerk.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.upvotes", is(mockPerk.getUpvotes() + 1)));
    }

    @Test
    void testDownvotePerk() throws Exception {
        mockMvc.perform(post("/api/perks/{id}/downvote", mockPerk.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.downvotes", is(mockPerk.getDownvotes() + 1)));
    }

//    @Test
//    void testDeletePerk() throws Exception {
//        mockMvc.perform(delete("/api/perks/{id}", mockPerk.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//
//        // Ensure the perk is deleted
//        Optional<Perk> deletedPerk = perkRepository.findById(mockPerk.getId());
//        assertFalse(deletedPerk.isPresent());
//    }
}
