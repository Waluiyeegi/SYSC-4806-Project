package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.example.model.Membership;

@Entity
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;
  
    private String product;
    private String description;

    private String geographicArea;

    private LocalDate expiryDate; // Added expiryDate field

    private int upvotes;
    private int downvotes;
  
    @Column(nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id") // This ensures a foreign key is created
    private User user;

    public Perk (){
        upvotes = 0;
        downvotes = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    
    //Save for Milestone 3
//    public Perk(String value, List<Restriction> restrictions, int code, Product product, Membership membership, String description){
//        name = value + " " + product.getName() + " with " + membership.getName();
//        this.restrictions = new ArrayList<>(restrictions);
//        this.code = code;
//        this.description = description;
//        upvotes = 0;
//        downvotes = 0;
//
//        this.product = product;
//        this.membership = membership;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }


    public void upvote(){
        upvotes += 1;
    }

    public void downvote(){
        downvotes += 1;
    }

    public void addDescription(String description){
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setMembership(Membership membership){
        this.membership = membership;
    }
    public Membership getMembership(){return membership;}

    public void setGeographicArea(String geographicArea){
        this.geographicArea = geographicArea;
    }

    public String getGeographicArea() {
        return geographicArea;
    }
    public String getCode() {return code;}

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }

    public int getUpvotes (){
        return upvotes;
    }

    public int getDownvotes (){
        return downvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String toString(){
        String str = "Name: " + getName() + "\n";
//        for(Restriction restriction : restrictions){
//            str += restriction.toString() + "\n";
//        }
        str += "Upvotes: " + upvotes + "\n";
        str += "Downvotes: " + downvotes + "\n";
        str += "Code: " + code + "\n ------- \n";
        return str;
    }
}
