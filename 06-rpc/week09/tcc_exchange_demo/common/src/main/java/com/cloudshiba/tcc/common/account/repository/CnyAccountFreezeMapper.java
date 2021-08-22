package com.cloudshiba.tcc.common.account.repository;

import com.cloudshiba.tcc.common.account.dto.AccountChangeDTO;
import com.cloudshiba.tcc.common.account.entity.AccountFreeze;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface CnyAccountFreezeMapper {

    @Insert(" insert into `t_cny_freeze` (id,user_id,account_id,amount,exchange_id,created) " +
            " values ( #{id},#{userId},#{accountId},#{amount},#{exchangeId},#{created})")
    int save(AccountFreeze accountFreeze);

    @Delete("delete from `t_cny_freeze` where id = #{id} and user_id= #{userId}")
    int deleteById(AccountFreeze accountFreeze);

    @Delete("delete from `t_cny_freeze` where exchange_id = #{exchangeId} and user_id= #{userId}")
    int deleteByChangeDTO(AccountChangeDTO accountChangeDTO);

}
