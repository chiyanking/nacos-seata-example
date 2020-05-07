package com.xd.example.seata.service;

import com.xd.example.seata.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-seata-storage-server")
public interface RemoteStorageService {

    /**
     * 扣减库存
     */
    @GetMapping(value = "/product/reduceCount")
    R reduceCount(@RequestParam("productId") Integer productId, @RequestParam("count") Integer count);

}