package com.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "perk_value")
    private String value;
    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "perk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restriction> restrictions;
    private int upvotes;
    private int downvotes;
    private int code;
    //expiry date
    //private com.example.model.User postedBy;

    public Perk (){
        restrictions = new ArrayList<>();
        upvotes = 0;
        downvotes = 0;
    }

    public Perk(String value, List<Restriction> restrictions, int code, Product product, Membership membership){
        name = value + " " + product.getName() + " with " + membership.getName();
        this.restrictions = new ArrayList<>(restrictions);
        this.code = code;
        upvotes = 0;
        downvotes = 0;

        this.product = product;
        this.membership = membership;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addRestriction (Restriction restriction){
        restrictions.add(restriction);
    }

    public void addRestrictions (List<Restriction> restriction){
        restrictions.addAll(restriction);
    }

    public void upvote(){
        upvotes += 1;
    }

    public void downvote(){
        downvotes += 1;
    }

    public String getName (){
        return name;
    }

    public int getCode() {return code;}

    public List<Restriction> getRestrictions(){
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public int getUpvotes (){
        return upvotes;
    }

    public int getDownvotes (){
        return downvotes;
    }

    public String toString(){
        String str = "Name: " + getName() + "\n";
        for(Restriction restriction : restrictions){
            str += restriction.toString() + "\n";
        }
        str += "Upvotes: " + upvotes + "\n";
        str += "Downvotes: " + downvotes + "\n";
        str += "Code: " + code + "\n ------- \n";
        return str;
    }
}
