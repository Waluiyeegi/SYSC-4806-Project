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

@CrossOrigin(origins = {"https://proud-bush-068efce0f.5.azurestaticapps.net, http://localhost:5173"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

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
        System.out.println("Received payload: " + payload); // Debugging incoming payload

        try {
            String username = (String) payload.get("username");
            List<String> memberships = (List<String>) payload.get("memberships");

            if (username == null || username.trim().isEmpty()) {
                throw new RuntimeException("Username cannot be null or empty");
            }

            // Update user profile
            User updatedUser = userService.updateUserProfile(username, memberships);
            return ResponseEntity.ok(new UserDTO(updatedUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }

            // Map memberships to a list of strings
            List<String> memberships = user.getMemberships().stream()
                    .map(Membership::getName) // Ensure `Membership` has a `getName` method
                    .toList();

            return ResponseEntity.ok(new UserDTO(user.getUsername(), memberships));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
