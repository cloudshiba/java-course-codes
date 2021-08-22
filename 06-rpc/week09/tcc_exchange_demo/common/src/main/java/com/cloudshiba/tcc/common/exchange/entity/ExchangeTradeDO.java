package com.cloudshiba.tcc.common.exchange.entity;

import lombok.Data;

@Data
public class ExchangeTradeDO {
    private Long id;
    private Long userId;
    private Long outAccountId;
    private String outCurrencyType;
    private Long outAmount;
    private Long inAccountId;
    private String inCurrencyType;
    private Long inAmount;
    private Integer status;
    private Long created;
    private Long updated;
}