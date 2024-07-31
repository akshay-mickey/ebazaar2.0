package com.EBazaar.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	
	@Autowired
    private JavaMailSender javaMailSender;

	 public void sendRegistrationConfirmation(String to, String username) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Registration Successful");
	        message.setText("Dear " + username + ",\n\nYou have successfully registered with our application.\n\nBest regards,\nEBazaar");
	        javaMailSender.send(message);
	 }

}
