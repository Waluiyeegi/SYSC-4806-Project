import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody User profile) {
        try {
            List<String> membershipNames = profile.getMemberships()
                    .stream()
                    .map(Membership::getName)
                    .toList();

            User updatedUser = userService.updateUserProfile(profile.getUsername(), membershipNames);
            return ResponseEntity.ok(new UserDTO(updatedUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
