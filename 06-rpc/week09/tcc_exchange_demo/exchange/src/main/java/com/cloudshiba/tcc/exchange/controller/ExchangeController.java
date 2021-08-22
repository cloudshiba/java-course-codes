package com.cloudshiba.tcc.exchange.controller;

import com.cloudshiba.tcc.common.exchange.entity.ExchangeTradeDO;
import com.cloudshiba.tcc.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    private static final BigDecimal AMPLIFY = new BigDecimal("10000");

    /**
     * 人民幣兌換美元
     */
    @RequestMapping("/cny2usd")
    public ExchangeTradeDO exchangeCny2Usd(@RequestParam("uid") Long userId,
                                           @RequestParam("amount")Long amount) {
        return exchangeService.exchangeCny2Usd(userId, amount);
    }

    /**
     * 美元兌換人民幣
     */
    @RequestMapping("/usd2cny")
    public ExchangeTradeDO exchangeUsd2Cny(@RequestParam("uid") Long userId,
                                           @RequestParam("amount")Long amount) {
        return exchangeService.exchangeUsd2Cny(userId, amount);
    }
}
