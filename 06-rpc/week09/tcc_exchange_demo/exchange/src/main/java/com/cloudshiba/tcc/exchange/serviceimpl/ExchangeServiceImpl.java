package com.cloudshiba.tcc.exchange.serviceimpl;

import com.cloudshiba.tcc.common.account.api.CnyAccountService;
import com.cloudshiba.tcc.common.account.api.UsdAccountService;
import com.cloudshiba.tcc.common.exchange.entity.ExchangeTradeDO;
import com.cloudshiba.tcc.common.exchange.repository.ExchangeMapper;
import com.cloudshiba.tcc.common.generator.IdGenerator;
import com.cloudshiba.tcc.exchange.constant.ExchangeConstant;
import com.cloudshiba.tcc.exchange.service.ExchangeOperationService;
import com.cloudshiba.tcc.exchange.service.ExchangeRateService;
import com.cloudshiba.tcc.exchange.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    @DubboReference
    private CnyAccountService cnyAccountService;

    @DubboReference
    private UsdAccountService usdAccountService;

    private IdGenerator idGenerator = new IdGenerator(ExchangeConstant.EXCHANGE_WORKER);

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ExchangeOperationService exchangeOperationService;

    @Autowired
    private ExchangeMapper exchangeMapper;

    private final static BigDecimal AMPLIFY_FACTOR = new BigDecimal(10_000);

    @Override
    @Transactional
    public ExchangeTradeDO exchangeCny2Usd(final Long userId, final Long amount) {
        ExchangeTradeDO exchangeTrade = new ExchangeTradeDO();
        Long cnyAccountId = cnyAccountService.getAccountId(userId);
        if (null == cnyAccountId) {
            return exchangeTrade;
        }
        Long usdAccountId = usdAccountService.getAccountId(userId);
        if (null == usdAccountId) {
            return exchangeTrade;
        }
        BigDecimal rate = exchangeRateService.getExchangeRate(ExchangeConstant.CURRENCY_CNY, ExchangeConstant.CURRENCY_USD);
        if (null == rate) {
            return exchangeTrade;
        }
        generateExchangeTrade(userId, cnyAccountId, usdAccountId,
                ExchangeConstant.CURRENCY_CNY, ExchangeConstant.CURRENCY_USD, exchangeTrade);
        fillAmountWithCalculateExchangeRate(amount, rate, exchangeTrade);
        exchangeMapper.save(exchangeTrade);
        exchangeOperationService.doExchangeOperation(exchangeTrade);
        return exchangeTrade;
    }

    private ExchangeTradeDO generateExchangeTrade(final Long userId, final Long outAccountId, final Long inAccountId,
                                                  final String outCurrencyType, final String inCurrencyType,
                                                  final ExchangeTradeDO exchangeTrade) {
        exchangeTrade.setId(idGenerator.getId());
        exchangeTrade.setUserId(userId);
        exchangeTrade.setOutAccountId(outAccountId);
        exchangeTrade.setOutCurrencyType(outCurrencyType);
        exchangeTrade.setInAccountId(inAccountId);
        exchangeTrade.setInCurrencyType(inCurrencyType);
        exchangeTrade.setStatus(ExchangeConstant.STATUS_CREATE);
        return exchangeTrade;
    }

    private void fillAmountWithCalculateExchangeRate(final Long amount, final BigDecimal rate,
                                                     final ExchangeTradeDO exchangeTrade) {
        BigDecimal outAmountValue = new BigDecimal(amount).multiply(AMPLIFY_FACTOR);
        exchangeTrade.setOutAmount(outAmountValue.longValue());
        BigDecimal inAmountValue = outAmountValue.multiply(rate);
        exchangeTrade.setInAmount(inAmountValue.longValue());
    }

    @Override
    public ExchangeTradeDO exchangeUsd2Cny(final Long userId, final Long amount) {
        ExchangeTradeDO exchangeTrade = new ExchangeTradeDO();
        Long usdAccountId = usdAccountService.getAccountId(userId);
        if (null == usdAccountId) {
            return exchangeTrade;
        }
        Long cnyAccountId = cnyAccountService.getAccountId(userId);
        if (null == cnyAccountId) {
            return exchangeTrade;
        }
        BigDecimal rate = exchangeRateService.getExchangeRate(ExchangeConstant.CURRENCY_USD, ExchangeConstant.CURRENCY_CNY);
        if (null == rate) {
            return exchangeTrade;
        }
        generateExchangeTrade(userId, usdAccountId, cnyAccountId,
                ExchangeConstant.CURRENCY_USD, ExchangeConstant.CURRENCY_CNY, exchangeTrade);
        fillAmountWithCalculateExchangeRate(amount, rate, exchangeTrade);
        exchangeMapper.save(exchangeTrade);
        exchangeOperationService.doExchangeOperation(exchangeTrade);
        return exchangeTrade;
    }
}
