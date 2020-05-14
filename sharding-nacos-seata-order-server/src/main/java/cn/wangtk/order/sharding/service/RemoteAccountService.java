package cn.wangtk.order.sharding.service;

import cn.wangtk.order.sharding.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "sharding-nacos-seata-account")
public interface RemoteAccountService {

    /**
     * 扣减账户余额
     */
    @PostMapping("/account/reduceBalance")
    R reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money);
}
