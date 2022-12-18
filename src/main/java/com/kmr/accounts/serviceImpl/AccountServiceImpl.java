package com.kmr.accounts.serviceImpl;

import com.kmr.accounts.entity.AccountEntity;
import com.kmr.accounts.exceptions.AccountException;
import com.kmr.accounts.models.AccountDetails;
import com.kmr.accounts.repository.AccountRepository;
import com.kmr.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    AccountRepository repository;


    @Override
    public AccountDetails getAccountDetails(String id) {

        Optional<AccountEntity> entity = repository.findById(id);

        RestTemplate template = new RestTemplate();
       ResponseEntity<String> response =  template.getForEntity("http://localhost:9898/get/data",String.class, (Object) null);
        log.info("Rest Templte Response : {}", response.getBody());

        AccountDetails details = new AccountDetails();
        details.setId(UUID.randomUUID().toString());

        HttpEntity<AccountDetails> request = new HttpEntity<AccountDetails>(details);

        ResponseEntity<AccountDetails> postResponse = template.exchange("http://localhost:9898/add/data", HttpMethod.POST, request, AccountDetails.class , (Object) null);
        log.info("Rest Templte Post  Response : {}", postResponse.getBody());





        log.info("Account details is present ? : {}" , entity.isPresent());
        if(entity.isPresent()){
            return getAccountDetails(entity.get());
        } else {
            log.error("Account Details not found");
            throw new AccountException("Account not found", HttpStatus.NOT_FOUND);
          }
    }

    @Override
    public AccountDetails addAccountDetails(AccountDetails accountDetails) {
        return  getAccountDetails(repository.save(getAccountEntity(accountDetails)));
    }

    @Override
    public boolean deleteAccountDetails(String id) {
         repository.deleteById(id);
         return true;
    }

    @Override
    public AccountDetails updateAccountDetails(String id, AccountDetails accountDetails) {

        Optional<AccountEntity> entity = repository.findById(id);

        if(entity.isPresent()){

            AccountEntity updatedEntity = entity.get();
            updatedEntity.setId(id);
            updatedEntity.setName(accountDetails.getName());
            updatedEntity.setBalance(accountDetails.getBalance());
            updatedEntity.setBranchCode(accountDetails.getBranchCode());
            updatedEntity.setAddress(accountDetails.getAddress());
            updatedEntity.setAccountNumber(entity.get().getAccountNumber());

            return getAccountDetails(repository.save(updatedEntity));
        } else {
            throw new AccountException("Entity not present for updating", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<AccountDetails> getAllAccountDetails() {
        return repository.findAll().stream().map(entity -> getAccountDetails(entity)).collect(Collectors.toList());
    }


    private AccountDetails getAccountDetails(AccountEntity entity){
        AccountDetails accountDetails = new AccountDetails();

        accountDetails.setAccountNumber(entity.getAccountNumber());
        accountDetails.setAddress(entity.getAddress());
        accountDetails.setBalance(entity.getBalance());
        accountDetails.setBranchCode(entity.getBranchCode());
        accountDetails.setName(entity.getName());
        accountDetails.setId(entity.getId());

        return accountDetails;

    }

    private AccountEntity getAccountEntity(AccountDetails accountDetails){

        AccountEntity entity =new AccountEntity();

        entity.setAccountNumber(accountDetails.getAccountNumber());
        entity.setAddress(accountDetails.getAddress());
        entity.setBalance(accountDetails.getBalance());
        entity.setBranchCode(accountDetails.getBranchCode());
        entity.setName(accountDetails.getName());
        entity.setId(accountDetails.getId());

        return entity;
    }



}
