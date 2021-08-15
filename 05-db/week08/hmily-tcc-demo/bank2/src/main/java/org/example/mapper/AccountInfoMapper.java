package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.AccountInfo;

public interface AccountInfoMapper {
    @Update("update account_info set  frozen_balance = #{frozenBalance} " +
            "where  account_name = #{accountName}")
    int increaseAccountBalance(@Param("accountName") String accountName,@Param("frozenBalance") Double frozenBalance);

    @Update("update account_info set account_balance = account_balance + frozen_balance , frozen_balance = 0 " +
            "where frozen_balance > 0")
    int confirmAccountBalance();

    @Update("update account_info set  frozen_balance = 0 " +
            "where  frozen_balance >0 and account_name = #{accountName}")
    int cancelAccountBalance(@Param("accountName") String accountName);

    @Select("select id as 'id',account_name as 'accountName',account_balance as 'accountBalance', frozen_balance as 'frozenBalance'" +
            " from account_info where account_name = #{accountName}")
    AccountInfo selectByName(@Param("accountName") String accountName);
}
