package org.example.entity;

import lombok.Data;

@Data
public class AccountInfo  {
    private Long id;
    private String accountName;
    private Double accountBalance;
    private Double frozenBalance;

}