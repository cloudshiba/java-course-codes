package com.cloudshiba.tcc.exchange.serviceimpl;

import com.cloudshiba.tcc.common.account.api.CnyAccountService;
import com.cloudshiba.tcc.common.account.api.UsdAccountService;
import com.cloudshiba.tcc.common.account.dto.AccountChangeDTO;
import com.cloudshiba.tcc.common.exchange.entity.ExchangeTradeDO;
import com.cloudshiba.tcc.common.exchange.repository.ExchangeMapper;
import com.cloudshiba.tcc.exchange.constant.ExchangeConstant;
import com.cloudshiba.tcc.exchange.service.ExchangeOperationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ExchangeOperationServiceImpl implements ExchangeOperationService {

    @DubboReference
    private CnyAccountService cnyAccountService;

    @DubboReference
    private UsdAccountService usdAccountService;

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmStatus", cancelMethod = "cancelStatus")
    @Transactional
    public boolean doExchangeOperation(final ExchangeTradeDO exchangeTrade) {
        log.info("doExchangeOperation is called");
        log.info(exchangeTrade.toString());
        exchangeMapper.updatePrepareStatus(exchangeTrade);
        AccountChangeDTO accountChangeDTO = new AccountChangeDTO();
        accountChangeDTO.setUserId(exchangeTrade.getUserId());
        accountChangeDTO.setExchangeId(exchangeTrade.getId());
        accountChangeDTO.setChangeAmount(exchangeTrade.getOutAmount());
        accountChangeDTO.setAccountId(exchangeTrade.getOutAccountId());
        // 扣減換出帳戶的金額
        if(ExchangeConstant.CURRENCY_CNY.equals(exchangeTrade.getOutCurrencyType())) {
            cnyAccountService.accountDecrease(accountChangeDTO);
        } else {
            usdAccountService.accountDecrease(accountChangeDTO);
        }
        accountChangeDTO.setAccountId(exchangeTrade.getInAccountId());
        accountChangeDTO.setChangeAmount(exchangeTrade.getInAmount());
        // 增加換入的金額到換入帳戶
        if(ExchangeConstant.CURRENCY_CNY.equals(exchangeTrade.getInCurrencyType())) {
            cnyAccountService.accountIncrease(accountChangeDTO);
        } else {
            usdAccountService.accountIncrease(accountChangeDTO);
        }
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirmStatus(final ExchangeTradeDO exchangeTrade) {
        log.info("confirmStatus is called");
        exchangeMapper.updateSuccessStatus(exchangeTrade);
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancelStatus(final ExchangeTradeDO exchangeTrade) {
        log.info("cancelStatus is called");
        exchangeMapper.updateFailStatus(exchangeTrade);
        return Boolean.TRUE;
    }
}
