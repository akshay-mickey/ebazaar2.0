package com.EBazaar.bankaccountdetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.EBazaar.UserInfo.UserInfo;

@Repository
public interface BankAccountDetailsRepository extends CrudRepository<BankAccountDetails,Integer> {

	BankAccountDetails findByAccountNumber(Long accountNumber);


//	BankAccountDetails findByUserName( String byUserName);
	
	
	

}
