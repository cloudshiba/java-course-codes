package com.cloudshiba.tcc.common.account.api;

import com.cloudshiba.tcc.common.account.dto.AccountChangeDTO;
import org.dromara.hmily.annotation.Hmily;

public interface UsdAccountService {

    Long getAccountId(Long userId);

    @Hmily
    boolean accountDecrease(AccountChangeDTO accountChange);

    @Hmily
    boolean accountIncrease(AccountChangeDTO accountChange);

}
