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
@CrossOrigin(origins = "https://proud-bush-068efce0f.5.azurestaticapps.net, http://localhost:5173")
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
    public ResponseEntity<Perk> upvotePerk(@PathVariable("id") Long id) {
        Perk perk = perkRepository.findById(id).orElseThrow(() -> new RuntimeException("Perk not found"));
        perk.setUpvotes(perk.getUpvotes() + 1);
        return ResponseEntity.ok(perkRepository.save(perk));
    }

    @PostMapping("/{id}/downvote")
    public ResponseEntity<Perk> downvotePerk(@PathVariable("id") Long id) {
        Perk perk = perkRepository.findById(id).orElseThrow(() -> new RuntimeException("Perk not found"));
        perk.setDownvotes(perk.getDownvotes() + 1);
        return ResponseEntity.ok(perkRepository.save(perk));
    }

    @GetMapping("/membership")
    public List<Perk> getPerksByMemberships(@RequestParam List<String> memberships) {
        System.out.println("Received memberships: " + memberships); // Debug log
        if (memberships == null || memberships.isEmpty()) {
            // Convert Iterable<Perk> to List<Perk>
            return (List<Perk>) perkRepository.findAll();
        }
        List<Perk> perks = perkRepository.findByMemberships(memberships);
        System.out.println("Filtered perks: " + perks); // Debug log
        return perks;
    }




    @GetMapping
    public List<Perk> getPerks() {
        return (List<Perk>) perkRepository.findAll();
    }

    @PostMapping
    public Perk savePerk(@RequestBody Perk perk) {
        return perkManager.savePerk(perk);
    }

    @GetMapping("/uniqueGeographicAreas")
    public List<String> getUniqueGeographicAreas() {
        return perkRepository.findDistinctGeographicAreas();
    }

    @GetMapping("/geographicArea")
    public List<Perk> getPerksByGeographicAreas(@RequestParam List<String> geographicAreas) {
        return perkRepository.findByGeographicAreas(geographicAreas);
    }

    @GetMapping("/uniqueMemberships")
    public List<String> getUniqueMemberships() {
        return perkRepository.findDistinctMemberships();
    }

    @GetMapping("/uniqueProducts")
    public List<String> getUniqueProducts() {
        return perkRepository.findDistinctProducts();
    }

    @GetMapping("/product")
    public List<Perk> getPerksByProducts(@RequestParam List<String> products) {
        if (products == null || products.isEmpty()) {
            return (List<Perk>) perkRepository.findAll();
        }
        return perkRepository.findByProducts(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerk(@PathVariable Long id) {
        perkManager.deletePerk(id);
        return ResponseEntity.noContent().build();
    }
}