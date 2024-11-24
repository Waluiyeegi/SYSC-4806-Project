package com.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Membership {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perk> perks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Membership(){
        perks = new ArrayList<>();
    }

    public Membership(String name) {
        this.name = name;
        this.perks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Perk> getPerks() {
        return perks;
    }

    public void addPerk(Perk perk) {
        perks.add(perk);
    }

}
