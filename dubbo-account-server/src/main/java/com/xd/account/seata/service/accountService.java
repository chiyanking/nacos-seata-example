package com.xd.account.seata.service;

import java.math.BigDecimal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface accountService {

    /**
     * 扣减账户余额
     *
     * @param userId
     * @param balance
     * @return
     */
    boolean reduceBalance(Integer userId, BigDecimal balance);


    /**
     * 重置账户
     *
     * @param userId
     * @param balance
     * @return
     */
    boolean resetBalance(Integer userId, BigDecimal balance);
}
