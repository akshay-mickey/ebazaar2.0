package com.tcs.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Login endpoint
    
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {

        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Store user ID in session
            session.setAttribute("userId", user.getId());
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    } 
    
    

    // Endpoint to get the user ID from session
    @GetMapping("/userId")
    public ResponseEntity<String> getUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            return ResponseEntity.ok(userId.toString());
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }

    // Logout endpoint
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}