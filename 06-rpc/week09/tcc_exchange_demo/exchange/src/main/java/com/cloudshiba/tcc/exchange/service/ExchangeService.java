package com.cloudshiba.tcc.exchange.service;

import com.cloudshiba.tcc.common.exchange.entity.ExchangeTradeDO;

public interface ExchangeService {

    ExchangeTradeDO exchangeCny2Usd(Long userId, Long amount);
    ExchangeTradeDO exchangeUsd2Cny(Long userId, Long amount);

}
