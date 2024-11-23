package com.example.controller;

import com.example.model.Perk;
import com.example.model.PerkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perks")
@CrossOrigin(origins = "http://localhost:5173")
public class PerkManagerController {

    @Autowired
    private PerkManager perkManager;

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

}