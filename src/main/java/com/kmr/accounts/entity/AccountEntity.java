package com.kmr.accounts.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private Long accountNumber;
    private Double balance;
    private Integer branchCode;

}
