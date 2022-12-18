package com.kmr.accounts.exceptions;

import com.kmr.accounts.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = AccountException.class)
    public ResponseEntity<Object> handleAccountException(AccountException exception){

        ExceptionResponse response = new ExceptionResponse();
        response.setId(UUID.randomUUID().toString());
        response.setMsg(exception.getMessage());
        response.setStatusCode(response.getStatusCode());

        return new ResponseEntity<>(response, response.getStatusCode());
    }




}
