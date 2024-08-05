package com.tcs.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
   
    public String registerUser(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            return "Email already exists";
        }
        if (!isValidPassword(password)) {
            return "Invalid password";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);  
        userRepository.save(user);
        return "Registration successful";
    }
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

//    // Register a new user
//    public void registerUser(User user) {
//    	
//        userRepository.save(user);
//    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}