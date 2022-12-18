package com.kmr.accounts.controller;


import com.kmr.accounts.models.AccountDetails;
import com.kmr.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@AllArgsConstructor
@Slf4j
public class AccountController {

    AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDetails> getAccountDetails(@PathVariable String id){
        log.info("Fetching account details for ID : {}",id);
        return new ResponseEntity<>(accountService.getAccountDetails(id), HttpStatus.OK);
    }


    @PostMapping("/account")
    public AccountDetails addAccountDetails(@RequestBody AccountDetails accountDetails){
        return accountService.addAccountDetails(accountDetails);
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccountDetails(@PathVariable String id){
        return accountService.deleteAccountDetails(id) ? "Account deleted successfully" : "Something went wrong, try again";
    }

    @GetMapping("/accounts")
    public List<AccountDetails> getAllAccounts(){
        return accountService.getAllAccountDetails();
    }

    @PutMapping("/account/{id}")
    public AccountDetails updateAccountDetails(@PathVariable String id, @RequestBody AccountDetails accountDetails){
        return accountService.updateAccountDetails(id,accountDetails);
    }


}
