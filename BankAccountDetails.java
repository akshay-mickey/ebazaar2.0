package com.EBazaar.bankaccountdetails;

import com.EBazaar.UserInfo.UserInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_account_details")
public class BankAccountDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Integer bankId;
	
	 	

	
	private String bankAccountType;
	
	private Long accountNumber;
	
	
	public BankAccountDetails(Integer bankId, UserInfo userInfo, String bankAccountType, Long accountNumber) {
		super();
		this.bankId = bankId;
		
		this.bankAccountType = bankAccountType;
		this.accountNumber = accountNumber;
	}
	
	BankAccountDetails(){
		
	}
	
	@Override
	public String toString() {
		return "BankAccountDetails [bank_id=" + bankId + ", bankAccountType=" + bankAccountType
				+ ", accountNumber=" + accountNumber + "]";
	}

	
	
	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	
	

}
