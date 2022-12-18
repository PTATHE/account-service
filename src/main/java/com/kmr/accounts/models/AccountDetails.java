package com.kmr.accounts.models;

import lombok.Data;

@Data
public class AccountDetails {

    private String id;
    private String name;
    private String address;
    private Long accountNumber;
    private Double balance;
    private Integer branchCode;




}
