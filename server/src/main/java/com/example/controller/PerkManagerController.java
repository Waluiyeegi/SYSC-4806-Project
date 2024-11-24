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
        if (perk.getName() == null || perk.getDescription() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(perkRepository.save(perk));
    }

    @GetMapping
    public List<Perk> getPerks()
    {
        return perkManager.getPerks();
    }

    @PostMapping
    public Perk savePerk(@RequestBody Perk perk)
    {
        return perkManager.savePerk(perk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerk(@PathVariable int id) {
        perkManager.deletePerk(id);
        return ResponseEntity.noContent().build();
    }
}