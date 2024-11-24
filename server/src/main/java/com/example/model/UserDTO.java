package com.example.model;

import java.util.List;

public class UserDTO {
    private String username;
    private List<String> memberships;

    // Constructor for username only
    public UserDTO(String username) {
        this.username = username;
    }

    // Constructor for username and memberships
    public UserDTO(String username, List<String> memberships) {
        this.username = username;
        this.memberships = memberships;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<String> memberships) {
        this.memberships = memberships;
    }
}
