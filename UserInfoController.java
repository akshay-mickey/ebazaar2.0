package com.EBazaar.UserInfo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo/api/v1.0")
public class UserInfoController {

	@Autowired
	UserInfoService service;
	
	@Autowired
    private EmailService emailService;

	@GetMapping("/allUsers")
	List<UserInfo> getAllUser() {

		return service.getAllUser();
		

	}

	@PostMapping("/addUser")
	String addnewUser(@RequestBody UserInfo user) {

		return service.saveUserInfo(user);
		
		
	}

	@GetMapping("/username/{name}")
	List<UserInfo> getByUserName(@PathVariable String name) {

		return service.getUserByUserName(name);

	}
	
	@GetMapping("/id/{id}")
	Optional<UserInfo> findById(@PathVariable Integer id) {
		
		return service.getById(id);
		
	}
	
	@GetMapping("/phonenumber/{phoneNumber}")
	UserInfo getbyPhoneNumber(Long phoneNumber) {
		
		return service.getbyPhoneNumber(phoneNumber);
	}
	
	@GetMapping("/update/{user}")
	void updateUserInfo(UserInfo user) {
		
		service.updateUserInfo(user);
	}

}
