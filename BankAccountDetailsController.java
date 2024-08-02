package com.EBazaar.bankaccountdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankaccount/api/v1.0")
public class BankAccountDetailsController {

	@Autowired
	BankAccountDetailsService service;

	@GetMapping("/allaccounts")
	List<BankAccountDetails> getAllAccount() {

		return service.getAllAccount();
	}

	@GetMapping("/id/{bankId}")
	BankAccountDetails getAccountByBankId(@PathVariable Integer bankId) {

		return service.getAccountByBankId(bankId);
	}

	@GetMapping("/accountnumber/{accountNumber}")
	BankAccountDetails getAccountByAccountNumber(@PathVariable Long accountNumber) {

		return service.getAccountByAccountNumber(accountNumber);
	}
//	 @PostMapping("/createaccount")
//	public BankAccountDetails createBankAccountDetails(@RequestBody BankAccountDetails bankAccountDetails) {
//        return service.saveBankAccountDetails(bankAccountDetails);
//    }

    @GetMapping("/{id}")
    public BankAccountDetails getBankAccountDetails(@PathVariable Integer id) {
        return service.getBankAccountDetailsById(id);
    }


	@PutMapping("/update")
	void updateAccount(@RequestBody BankAccountDetails account) {

		service.updateAccount(account);
	}

	
}

