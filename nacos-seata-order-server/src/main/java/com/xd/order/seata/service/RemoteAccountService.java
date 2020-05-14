package com.xd.order.seata.service;

import com.xd.order.seata.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "nacos-seata-account-server")
public interface RemoteAccountService {

    /**
     * 扣减账户余额
     */
    @PostMapping("/account/reduceBalance")
    R reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money);
}
