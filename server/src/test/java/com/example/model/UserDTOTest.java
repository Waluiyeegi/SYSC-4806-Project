package com.example.model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void testConstructorWithUsernameOnly() {
        UserDTO userDTO = new UserDTO("testUser");

        assertEquals("testUser", userDTO.getUsername());
        assertNull(userDTO.getMemberships());
    }

    @Test
    void testConstructorWithUsernameAndMemberships() {
        List<String> memberships = List.of("Costco", "United");
        UserDTO userDTO = new UserDTO("testUser", memberships);

        assertEquals("testUser", userDTO.getUsername());
        assertEquals(memberships, userDTO.getMemberships());
    }

    @Test
    void testGetUsername() {
        UserDTO userDTO = new UserDTO("testUser");

        assertEquals("testUser", userDTO.getUsername());
    }

    @Test
    void testSetUsername() {
        UserDTO userDTO = new UserDTO("testUser");
        userDTO.setUsername("newUser");

        assertEquals("newUser", userDTO.getUsername());
    }

    @Test
    void testGetMemberships() {
        List<String> memberships = List.of("Costco", "United");
        UserDTO userDTO = new UserDTO("testUser", memberships);

        assertEquals(memberships, userDTO.getMemberships());
    }

    @Test
    void testSetMemberships() {
        UserDTO userDTO = new UserDTO("testUser");
        List<String> memberships = List.of("Costco", "United");
        userDTO.setMemberships(memberships);

        assertEquals(memberships, userDTO.getMemberships());
    }

    @Test
    void testEmptyMemberships() {
        UserDTO userDTO = new UserDTO("testUser", List.of());

        assertNotNull(userDTO.getMemberships());
        assertTrue(userDTO.getMemberships().isEmpty());
    }
}
