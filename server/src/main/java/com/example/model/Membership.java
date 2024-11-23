package com.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Membership {

    @Column(nullable = false)
    private String name;
    private List<Perk> perks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Membership(){
        perks = new ArrayList<>();
    }

    public Membership(String name, List<Perk> initialPerks){
        perks = new ArrayList<>(initialPerks);
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addPerk(Perk perk){
        perks.add(perk);
    }

    public void addPerks(List<Perk> perk){
        perks.addAll(perk);
    }

    public String getName(){
        return name;
    }

    public List<Perk> getPerks(){
        return perks;
    }
}