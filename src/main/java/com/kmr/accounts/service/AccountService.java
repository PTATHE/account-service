package com.kmr.accounts.service;

import com.kmr.accounts.models.AccountDetails;

import java.util.List;

public interface AccountService {


    public AccountDetails getAccountDetails(String id);

    public AccountDetails addAccountDetails(AccountDetails accountDetails);

    public boolean deleteAccountDetails(String id);

    public AccountDetails updateAccountDetails(String id, AccountDetails accountDetails);

    public List<AccountDetails> getAllAccountDetails();

}
