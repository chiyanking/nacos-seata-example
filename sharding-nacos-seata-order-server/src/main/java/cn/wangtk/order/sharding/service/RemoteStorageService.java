package cn.wangtk.order.sharding.service;

import cn.wangtk.order.sharding.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sharding-nacos-seata-storage")
public interface RemoteStorageService {

    /**
     * 扣减库存
     */
    @GetMapping(value = "/product/reduceCount")
    R reduceCount(@RequestParam("productId") Integer productId, @RequestParam("count") Integer count);

}