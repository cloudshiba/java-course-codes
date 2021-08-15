package org.example.service;

import org.example.entity.AccountInfo;

public interface Bank1AccountService {
    Boolean decreaseBalance(String name,Double amount);

    AccountInfo selectByName(String accountName);
}
