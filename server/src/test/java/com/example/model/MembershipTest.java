package com.example.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.example.model.Perk;
import com.example.model.Membership;
import com.example.model.User;

import java.util.ArrayList;
import java.util.List;

class MembershipTest {

    @Test
    public void MembershipTest(){
        String name = "Test Name";
        User user = new User();
        Perk perk1 = new Perk();

        Membership membership = new Membership();

        membership.setName(name);
        membership.setPerk(perk1);
        membership.setUser(user);

        assertEquals(membership.getName(), name);
        assertEquals(membership.getPerks().get(0), perk1);
        assertEquals(membership.getUser(), user);
    }
}