package com.tcs.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JavaMailSender javaMailSender;
   
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
     
        else {
        	 userService.registerUser(user.getEmail(),user.getPassword());
        	 sendRegistrationConfirmation(user.getEmail());
             return ResponseEntity.ok("User registered successfully");
        }
    }
    
    public void sendRegistrationConfirmation(String to) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registration Successful");
        message.setText("Dear "  + ",\n\nYou have successfully registered with E- Bazaar application with your e-mail id - "+to +".\n\nBest regards,\nEBazaar");
        javaMailSender.send(message);
     }

    // Login a user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Find user by email
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid login details");
        }

        // Return success message and user ID
        return ResponseEntity.ok(Map.of(
            "message", "Login successful",
            "userId", existingUser.getId()
        ));
    }

    // Get user by ID (for example, for profile page)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}