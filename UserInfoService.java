package com.EBazaar.UserInfo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

	@Autowired
	UserInfoRepository repo;
	
	@Autowired
	EmailService emailService;
	
//	 @Autowired
//	    private ConfirmationMail confirmationMail;

	// To get Information about all Users
	List<UserInfo> getAllUser() {

		return ((List) (repo.findAll()));

	}

	 public String saveUserInfo(UserInfo userInfo) {
	         repo.save(userInfo);

			 emailService.sendRegistrationConfirmation(userInfo.getEmail(), userInfo.getFirstName());
	        return "User registered successfully. A confirmation email has been sent.";
	    }
	List<UserInfo> getUserByUserName( String name) {

		return repo.findByUserName(name);
	}
	
	public Optional<UserInfo> getById(Integer id) {
		
		return repo.findById(id);
		
	}
	
	UserInfo getbyPhoneNumber(Long phoneNumber) {
		
		return repo.findByPhoneNumber(phoneNumber);
	}
	
	void updateUserInfo(UserInfo user) {
		
		repo.save(user);
	}

	

}
