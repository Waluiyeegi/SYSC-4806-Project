package com.example.controller;

import com.example.model.Perk;
import com.example.model.Membership;
import com.example.model.PerkManager;
import com.example.repository.PerkRepository;
import com.example.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/perks")
@CrossOrigin(origins = "https://proud-bush-068efce0f.5.azurestaticapps.net, http://localhost:5173")
public class PerkManagerController {

    @Autowired
    private PerkRepository perkRepository;

    @Autowired
    private PerkManager perkManager;

    @Autowired
    private MembershipRepository membershipRepository;

    @PostMapping("/addNewPerk")
    public ResponseEntity<Perk> addNewPerk(@RequestBody Perk perk) {
        if (perk.getProduct() == null || perk.getName() == null || perk.getCode() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Perk savedPerk = perkRepository.save(perk);
        return ResponseEntity.ok(savedPerk);
    }
    @PostMapping("/createPerk")
    public ResponseEntity<Perk> createPerk(@RequestBody Map<String, Object> payload) {
        Long membershipId = Long.valueOf(payload.get("membership").toString());
        String description = payload.get("description").toString();
        String product = payload.get("product").toString();
        String geographicArea = payload.get("geographicArea").toString();
        String expiryDate = payload.get("expiryDate").toString();
        String code = payload.get("code").toString();
        String perkName = payload.get("perkName").toString();

        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        //System.out.println(membership.getId());

        Perk perk = new Perk();
        perk.setName(perkName);
        perk.setMembership(membership);
        perk.addDescription(description);
        perk.setProduct(product);
        perk.setGeographicArea(geographicArea);
        perk.setCode(code);
        if (!Objects.equals(expiryDate, " ")) {
            perk.setExpiryDate(LocalDate.parse(expiryDate));
        }

        Perk savedPerk = perkRepository.save(perk);
        return ResponseEntity.ok(savedPerk);//.status(HttpStatus.CREATED).body(savedPerk); // //
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
//            return perkRepository.findAll(); // Return all perks if no membership filters
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

    @GetMapping("/uniqueMemberships")
    public List<String> getUniqueMemberships() {
        return perkRepository.findDistinctMemberships();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerk(@PathVariable Long id) {
        perkManager.deletePerk(id);
        return ResponseEntity.noContent().build();
    }
}