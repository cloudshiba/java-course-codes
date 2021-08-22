package com.cloudshiba.tcc.exchange.service;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal getExchangeRate(String outCurrencyType, String inCurrencyType);

}
