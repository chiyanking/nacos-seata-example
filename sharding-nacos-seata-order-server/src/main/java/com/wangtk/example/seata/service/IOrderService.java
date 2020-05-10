package com.wangtk.example.seata.service;

import com.wangtk.example.seata.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface IOrderService extends IService<Order> {

    void createOrder(Order order);

    void resetOrder();

    void createOrderMultiDB(Boolean rollBack);
}
