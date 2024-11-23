package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Perk {

    @Id
    private int id;
    private String name;
    private String value;
    private Membership membership;
    private Product product;

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
