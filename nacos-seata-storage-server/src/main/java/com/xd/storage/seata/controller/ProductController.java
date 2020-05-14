package com.xd.storage.seata.controller;


import com.xd.storage.seata.service.IProductService;
import com.xd.storage.seata.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 仓储服务 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 扣减库存
     */
    @RequestMapping("/reduceCount")
    public R decrease(@RequestParam Integer count, @RequestParam Integer productId) {
        boolean reduceCount = productService.reduceCount(count, productId);
        return R.builder().success(reduceCount).msg("扣除库存成功").build();
    }

    /**
     * 重置库存
     */
    @RequestMapping("/resetCount")
    public R resetCount(@RequestParam Integer productId, @RequestParam Integer count) {
        return R.builder().success(productService.resetCount(productId, count)).msg("重置成功").build();
    }
}

