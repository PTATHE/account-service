package com.kmr.accounts.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AccountException extends RuntimeException{

   public AccountException(){
        super();
    }

   public AccountException(String msg, HttpStatus status){
        super(msg);
    }

   public AccountException(String msg, Throwable ex){
        super(msg,ex);
    }

}
