package com.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("user1", "pw", List.of());
    }

    @Test
    void testGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void testSetId() {
        user.setId(2L);
        assertEquals(2L, user.getId());
    }

    @Test
    void testSetUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    void testGetUsername() {
        assertEquals("user1", user.getUsername());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newPw");
        assertEquals("newPw", user.getPassword());
    }

    @Test
    void testGetPassword() {
        assertEquals("pw", user.getPassword());
    }

    @Test
    void testAuthenticateCorrectPassword() {
        assertTrue(user.authenticate("pw"));
    }

    @Test
    void testAuthenticateIncorrectPassword() {
        assertFalse(user.authenticate("wrongPw"));
    }

    @Test
    void testAddMembership() {
        Membership membership = new Membership();
        membership.setName("Costco");
        user.addMembership(membership);

        assertEquals(1, user.getMemberships().size());
        assertEquals("Costco", user.getMemberships().get(0).getName());
    }

    @Test
    void testGetMemberships() {
        Membership membership1 = new Membership();
        membership1.setName("Costco");
        Membership membership2 = new Membership();
        membership2.setName("Airmiles");

        user.addMembership(membership1);
        user.addMembership(membership2);

        List<Membership> memberships = user.getMemberships();
        assertEquals(2, memberships.size());
        assertTrue(memberships.stream().anyMatch(m -> m.getName().equals("Costco")));
        assertTrue(memberships.stream().anyMatch(m -> m.getName().equals("Airmiles")));
    }

    @Test
    void testSetMemberships() {
        Membership membership1 = new Membership();
        membership1.setName("Costco");
        Membership membership2 = new Membership();
        membership2.setName("Airmiles");

        user.setMemberships(List.of(membership1, membership2));

        List<Membership> memberships = user.getMemberships();
        assertEquals(2, memberships.size());
        assertTrue(memberships.stream().anyMatch(m -> m.getName().equals("Costco")));
        assertTrue(memberships.stream().anyMatch(m -> m.getName().equals("Airmiles")));
    }

    @Test
    void testAddVote() {
        Perk perk = new Perk();
        perk.setName("Test Perk");

        user.addPerk(perk);

        assertEquals(1, user.getPerks().size());
        assertEquals("Test Perk", user.getPerks().get(0).getName());
    }

    @Test
    void testRemovePerk() {
        Perk perk = new Perk();
        perk.setName("Test Perk");

        user.addPerk(perk);
        assertEquals(1, user.getPerks().size());

        user.removePerk(perk);
        assertEquals(0, user.getPerks().size());
    }

    @Test
    void testGetPerks() {
        Perk perk1 = new Perk();
        perk1.setName("Perk One");
        Perk perk2 = new Perk();
        perk2.setName("Perk Two");

        user.addPerk(perk1);
        user.addPerk(perk2);

        List<Perk> perks = user.getPerks();
        assertEquals(2, perks.size());
        assertTrue(perks.stream().anyMatch(p -> p.getName().equals("Perk One")));
        assertTrue(perks.stream().anyMatch(p -> p.getName().equals("Perk Two")));
    }
}
