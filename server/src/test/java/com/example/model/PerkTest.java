package com.example.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.example.model.Perk;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.ArrayList;

class PerkTest {

    @Test
    public void PerkTest(){
        Perk perk = new Perk();

        String description = "Test Description";
        String name = "Test Name";
        Membership membership = new Membership("Test Membership");
        String code = "test Code";
        String geoArea = "test area";
        String product = "Test Product";
        LocalDate expiryDate = LocalDate.of(2024, 11, 25);

        perk.addDescription(description);
        perk.setName(name);
        perk.setMembership(membership);
        perk.downvote();
        perk.upvote();
        perk.setCode(code);
        perk.setGeographicArea(geoArea);
        perk.setProduct(product);
        perk.setExpiryDate(expiryDate);

        assertEquals(perk.getDescription(), description);
        assertEquals(perk.getName(), name);
        assertEquals(perk.getMembership(), membership);
        assertEquals(perk.getUpvotes(), 1);
        assertEquals(perk.getDownvotes(), 1);
        assertEquals(perk.getCode(), code);
        assertEquals(perk.getGeographicArea(), geoArea);
        assertEquals(perk.getProduct(), product);
        assertEquals(perk.getExpiryDate(), expiryDate);
    }
}