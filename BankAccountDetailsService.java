package com.EBazaar.bankaccountdetails;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBazaar.UserInfo.UserInfo;


@Service
public class BankAccountDetailsService {
	
	
	@Autowired
    private BankAccountDetailsRepository repo1;

   
   

    List<BankAccountDetails> getAllAccount(){
    	
    	return ((List)repo1.findAll());
    }
    
    BankAccountDetails getAccountByBankId(Integer bankId) {
    	
    	return repo1.findById(bankId).get();
    }
    
    BankAccountDetails getAccountByAccountNumber(Long accountNumber) {
    	
    	return repo1.findByAccountNumber(accountNumber);
    }
  
    void updateAccount(BankAccountDetails account) {
    	
    	repo1.save(account);
    }
//    public void createAccount(Integer id  , long accountnum ,String BankAccountType) {
//    	UserInfo user = new UserInfo();
//        user.setId(id);
//
//        BankAccountDetails userProfile = new BankAccountDetails();
//        userProfile.setUserInfo(user); // Set bidirectional relationship
//        userProfile.setBankAccountType(BankAccountType);
//        userProfile.setAccountNumber(accountnum);
//
//        user.setBankAccountDetails(userProfile); // Set bidirectional relationship
//
//        repo1.save(user);
//    }

    public BankAccountDetails getBankAccountDetailsById(Integer id) {
        return repo1.findById(id).orElse(null);
    }

}
