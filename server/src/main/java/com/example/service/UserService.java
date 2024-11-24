package com.example.service;

import com.example.model.Membership;
import com.example.model.User;
import org.springframework.stereotype.Service;
import com.example.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Add password hashing in production
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.authenticate(password)) // Matches hashed password
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }

    public User updateUserProfile(String username, List<String> memberships) {
        // Find the user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Clear existing memberships
        user.getMemberships().clear();

        // Create new memberships and associate them with the user
        List<Membership> updatedMemberships = memberships.stream()
                .map(name -> new Membership(name, user))
                .collect(Collectors.toList());

        // Add memberships to the user
        user.setMemberships(updatedMemberships);

        // Save and return the updated user
        return userRepository.save(user);
    }

    // Retrieve user by username
    public User findByUsername(String username) {
        System.out.println("Finding user by username: " + username); // Debugging log
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}

