package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.example.mapper.AccountInfoMapper;
import org.example.service.Bank2AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bank2AccountService")
@Slf4j
public class Bank2AccountServiceImpl implements Bank2AccountService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public boolean increaseAccountBalance(String accountName, Double amount) {
        accountInfoMapper.increaseAccountBalance(accountName, amount);
        log.info("******** Bank2 Service Begin try ...");
        return Boolean.TRUE;

    }

    @Override
    public String hi(String serverName) {
        return "hello，" + serverName;
    }


    public void confirmMethod(String accountName, Double amount) {
        accountInfoMapper.confirmAccountBalance();
        log.info("******** Bank2 Service commit...  ");
    }

    public void cancelMethod(String accountName, Double amount) {
        accountInfoMapper.cancelAccountBalance(accountName);
        log.info("******** Bank2 Service begin cancel...  ");

    }
}
