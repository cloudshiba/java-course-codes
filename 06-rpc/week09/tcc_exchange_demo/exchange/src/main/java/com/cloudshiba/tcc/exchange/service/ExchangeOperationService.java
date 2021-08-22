package com.cloudshiba.tcc.exchange.service;

import com.cloudshiba.tcc.common.exchange.entity.ExchangeTradeDO;
import org.dromara.hmily.annotation.Hmily;

public interface ExchangeOperationService {

    @Hmily
    boolean doExchangeOperation(ExchangeTradeDO exchangeTrade);

}
