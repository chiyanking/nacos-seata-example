package cn.wangtk.order.sharding.controller;


import cn.wangtk.order.sharding.domain.Order;
import cn.wangtk.order.sharding.service.IOrderService;
import cn.wangtk.order.sharding.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public R create(Order order) {
        orderService.createOrder(order);
        return R.builder().msg("订单创建成功").build();
    }


    @PostMapping("/reset")
    public R reset() {
        orderService.resetOrder();
        return R.builder().msg("重置订单成功").build();
    }

    @PostMapping("/createOrderMultiDB")
    public R createOrderMultiDB(Boolean rollBack) {
        orderService.createOrderMultiDB(rollBack);
        return R.builder().msg("添加成功").build();
    }
}

