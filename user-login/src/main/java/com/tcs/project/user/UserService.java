package com.tcs.project.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a user with email and password
    public String registerUser(String email, String password) {
        // Check if the email already exists
        if (userRepository.findByEmail(email) != null) {
            return "Email already exists";
        }

        // Validate the password
        if (!isValidPassword(password)) {
            return "Invalid password";
        }

        // Create a new User object
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);  

        // Save the user to the database
        userRepository.save(user);
        return "Registration successful";
    }

    // Validate password criteria
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }
}


   

