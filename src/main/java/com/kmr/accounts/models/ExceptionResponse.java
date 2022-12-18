package com.kmr.accounts.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponse {

    private String msg;
    private String id;
    private HttpStatus statusCode;

}
