package com.example.controller;

import com.example.model.Perk;
import com.example.model.PerkManager;
import com.example.repository.PerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/perks")
@CrossOrigin(origins = "http://localhost:5173")
public class PerkManagerController {

    @Autowired
    private PerkRepository perkRepository;

    @Autowired
    private PerkManager perkManager;

    @PostMapping("/addNewPerk")
    public ResponseEntity<Perk> addNewPerk(@RequestBody Perk perk) {
        if (perk.getProduct() == null || perk.getName() == null || perk.getCode() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Perk savedPerk = perkRepository.save(perk);
        return ResponseEntity.ok(savedPerk);
    }

    @PostMapping("/{id}/upvote")
    public ResponseEntity<Perk> upvotePerk(@PathVariable Long id) {
        Perk perk = perkRepository.findById(id).orElseThrow(() -> new RuntimeException("Perk not found"));
        perk.setUpvotes(perk.getUpvotes() + 1);
        return ResponseEntity.ok(perkRepository.save(perk));
    }

    @PostMapping("/{id}/downvote")
    public ResponseEntity<Perk> downvotePerk(@PathVariable Long id) {
        Perk perk = perkRepository.findById(id).orElseThrow(() -> new RuntimeException("Perk not found"));
        perk.setDownvotes(perk.getDownvotes() + 1);
        return ResponseEntity.ok(perkRepository.save(perk));
    }

    @GetMapping
    public List<Perk> getPerks() {
        return (List<Perk>) perkRepository.findAll();
    }

    @PostMapping
    public Perk savePerk(@RequestBody Perk perk)
    {
        return perkManager.savePerk(perk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerk(@PathVariable Long id) {
        perkManager.deletePerk(id);
        return ResponseEntity.noContent().build();
    }
}