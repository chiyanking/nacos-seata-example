package cn.wangtk.account.sharding.service;

import cn.wangtk.account.sharding.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface IAccountService extends IService<Account> {

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
