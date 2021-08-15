package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.AccountInfo;

public interface AccountInfoMapper {
    @Update("update account_info set account_balance = account_balance - #{amount} , frozen_balance = frozen_balance + #{amount} " +
            "where account_balance > #{amount} and account_name = #{name}")
    int decreaseBalance(@Param("name") String name, @Param("amount") Double amount);

    @Update("update account_info set  frozen_balance = 0 " +
            "where frozen_balance > 0")
    int confirm();

    @Update("update account_info set account_balance = account_balance + frozen_balance , frozen_balance = 0 " +
            "where frozen_balance > 0")
    int cancel();

    @Select("select id as 'id',account_name as 'accountName',account_balance as 'accountBalance', frozen_balance as 'frozenBalance'" +
            " from account_info where account_name = #{name}")
    AccountInfo selectByName(String name);
}
