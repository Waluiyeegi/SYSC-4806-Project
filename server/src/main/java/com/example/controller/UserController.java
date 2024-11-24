package com.example.controller;

import com.example.model.Membership;
import com.example.model.User;
import com.example.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.UserService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {

            User registeredUser = userService.registerUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(new UserDTO(registeredUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(new UserDTO(loggedInUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


    @PostMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> payload) {
        try {
            String username = (String) payload.get("username");
            List<String> memberships = (List<String>) payload.get("memberships");

            // Update user profile
            User updatedUser = userService.updateUserProfile(username, memberships);
            return ResponseEntity.ok(new UserDTO(updatedUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
