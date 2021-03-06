package com.cloudshiba.tcc.common.exchange.repository;

import com.cloudshiba.tcc.common.exchange.entity.ExchangeTradeDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeMapper {

    @Insert("insert into `t_exchange` (id,user_id,out_account_id,out_currency_type,out_amount," +
            "in_account_id,in_currency_type,in_amount,status,created)" +
            " values ( #{id},#{userId},#{outAccountId},#{outCurrencyType},#{outAmount}," +
            "#{inAccountId},#{inCurrencyType},#{inAmount},#{status},unix_timestamp())")
    int save(ExchangeTradeDO exchangeTradeDO);

    @Update("update `t_exchange` set status=1, updated = now() where id=#{id} and status=0")
    int updatePrepareStatus(ExchangeTradeDO exchangeTradeDO);


    @Update("update `t_exchange` set status=2, updated = now() where id=#{id} and status=1")
    int updateSuccessStatus(ExchangeTradeDO exchangeTradeDO);

    @Update("update `t_exchange` set status=3, updated = now() where id=#{id} and status=1")
    int updateFailStatus(ExchangeTradeDO exchangeTradeDO);

}
