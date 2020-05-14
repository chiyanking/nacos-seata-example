package com.xd.account.seata.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.account.seata.domain.Account;
import com.xd.account.seata.mapper.AccountMapper;
import com.xd.account.seata.service.IAccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    @GlobalTransactional(name = "prex-create-order", rollbackFor = Exception.class)
    public boolean reduceBalance(Integer userId, BigDecimal balance) {

        log.info("当前 XID: {}", RootContext.getXID());
        checkBalance(userId, balance);

        log.info("开始扣减用户 {} 余额", userId);
        //模拟超时异常
//        try {
//            Thread.sleep(10 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Integer record = baseMapper.reduceBalance(userId, balance);
        log.info("结束扣减用户 {} 余额结果:{}", userId, record > 0 ? "操作成功" : "扣减余额失败");
        return record > 0;
    }

    @Override
    public boolean resetBalance(Integer userId, BigDecimal balance) {
        baseMapper.deleteUndoLog();
        return update(Wrappers.<Account>lambdaUpdate().eq(Account::getUserId, userId).set(Account::getBalance, balance));
    }

    private void checkBalance(Integer userId, BigDecimal price) {
        log.info("检查用户 {} 余额", userId);

        Optional<Account> account = Optional.ofNullable(baseMapper.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getUserId, userId)));
        if (account.isPresent()) {
            BigDecimal balance = account.get().getBalance();
            if (balance.compareTo(price) == -1) {
                log.warn("用户 {} 余额不足，当前余额:{}", userId, balance);
                throw new RuntimeException("余额不足");
            }
        }
    }
}
