package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.model.Membership;
import com.example.repository.MembershipRepository;
import com.example.repository.PerkRepository;
import com.example.model.Perk;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private PerkRepository perkRepository;

    @PostMapping("/createMembership")
    public ResponseEntity<Membership> createMembership(@RequestBody String name) {
        Membership savedMembership = membershipRepository.save(new Membership(name));
        return ResponseEntity.ok(savedMembership);
    }

    @PostMapping("/{membershipId}/perks")
    public ResponseEntity<Perk> addPerkToMembership(@PathVariable Long membershipId, @RequestBody Perk perk) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        perk.setMembership(membership);
        Perk savedPerk = perkRepository.save(perk);
        return ResponseEntity.ok(savedPerk);
    }

    @GetMapping
    public ResponseEntity<List<Membership>> getAllMemberships() {
        List<Membership> memberships = membershipRepository.findAll();
        return ResponseEntity.ok(memberships);
    }
}