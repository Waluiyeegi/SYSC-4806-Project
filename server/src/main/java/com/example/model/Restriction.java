package com.example.model;

import jakarta.persistence.*;

@Entity
public class Restriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "perk_id", nullable = false)
    private Perk perk;

    public Restriction() {}

    public Restriction(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Restriction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

