package com.xd.example.seata.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xd.example.seata.domain.Order;
import com.xd.example.seata.mapper.OrderMapper;
import com.xd.example.seata.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.example.seata.service.RemoteAccountService;
import com.xd.example.seata.service.RemoteStorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private RemoteStorageService remoteStorageService;

    @Autowired
    private RemoteAccountService remoteAccountService;

    @Override
    @GlobalTransactional(name = "prex-create-order", rollbackFor = Exception.class)
    public void createOrder(Order order) {
        log.info("当前 XID: {}", RootContext.getXID());
        log.info("下单开始,用户:{},商品:{},数量:{},金额:{}", order.getUserId(), order.getProductId(), order.getCount(), order.getPayMoney());
        //创建订单
        order.setStatus(0);
        boolean save = save(order);
        log.info("保存订单{}", save ? "成功" : "失败");

        //远程调用库存服务扣减库存
        log.info("扣减库存开始");
        remoteStorageService.reduceCount(order.getProductId(), order.getCount());
        log.info("扣减库存结束");

        //远程调用账户服务扣减余额
        log.info("扣减余额开始");
        remoteAccountService.reduceBalance(order.getUserId(), order.getPayMoney());
        log.info("扣减余额结束");

        //修改订单状态为已完成
        log.info("修改订单状态开始");
        order.setStatus(1);
        updateById(order);
        log.info("修改订单状态结束");

        log.info("下单结束");
    }

    @Override
    public void resetOrder() {
        remove(Wrappers.emptyWrapper());
        baseMapper.deleteUndoLog();
    }

    public static void main(String[] args) {
        RootContext.bind("123123123");
        String unbind = RootContext.unbind();
        System.out.println(unbind);
    }
}
