package com.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perk> perks;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membership> memberships;

    public User() {
        this.memberships = new ArrayList<>();
        this.perks = new ArrayList<>();
    }

    public User(String username, String password, List<Membership> memberships){
        this.username = username;
        this.password = password;
        this.memberships = new ArrayList<>(memberships);
        this.perks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addMembership(Membership membership) {
        memberships.add(membership);
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void addVote (Perk perk){
        perks.add(perk);
    }

    public void removeVote (Perk perk){
        perks.remove(perk);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public List<Perk> getPerks(){
        return perks;
    }

    public String toString(){
        String str = "";
        for (Perk perk : perks){
            str += perk.toString() + "\n";
        }
        return str;
    }
}
