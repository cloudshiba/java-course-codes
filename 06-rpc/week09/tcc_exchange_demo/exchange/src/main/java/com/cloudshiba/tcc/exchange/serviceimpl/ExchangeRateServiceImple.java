package com.cloudshiba.tcc.exchange.serviceimpl;

import com.cloudshiba.tcc.exchange.constant.ExchangeConstant;
import com.cloudshiba.tcc.exchange.service.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 模擬匯率查詢服務，提供人民幣對美元或美元對人民幣的匯率查詢
 */
@Service
public class ExchangeRateServiceImple implements ExchangeRateService {
    /**
     * 簡易模擬匯率查詢
     * @param outCurrencyType  匯出的幣別，僅限人民幣(CNY)和美元(USD)
     * @param inCurrencyType  匯入的幣別，僅限人民幣(CNY)和美元(USD)
     * @return
     */
    @Override
    public BigDecimal getExchangeRate(final String outCurrencyType, final String inCurrencyType) {

        // 人民幣兌換美元，1 人民幣換 1 / 7 美元
        if(ExchangeConstant.CURRENCY_CNY.equals(outCurrencyType) &&
                ExchangeConstant.CURRENCY_USD.equals(inCurrencyType)) {
            return new BigDecimal("0.1428");
        }

        // 美元兌換人民幣，1 美元換 7 人民幣
        if(ExchangeConstant.CURRENCY_USD.equals(outCurrencyType) &&
                ExchangeConstant.CURRENCY_CNY.equals(inCurrencyType)) {
            return new BigDecimal("7.0000");
        }

        return null;
    }
}