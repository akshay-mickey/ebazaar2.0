package com.tcs.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class UserController {

    @Autowired
    private UserService userService;


	@Autowired
    private JavaMailSender javaMailSender;
	
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        String result = userService.registerUser(email, password);
        if (result.equals("Registration successful")) {
        	sendRegistrationConfirmation(email);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    
    public void sendRegistrationConfirmation(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registration Successful");
        message.setText("Dear "  + ",\n\nYou have successfully registered with E- Bazaar application with your e-mail id - "+to +".\n\nBest regards,\nEBazaar");
        javaMailSender.send(message);
 }
}

