package com.wangtk.ribbon.service;

import com.wangtk.ribbon.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class OrderServiceTest extends BaseTest {

    @Resource
    OrderService orderService;

    @Test
    public void testGetOrderService() {
        orderService.getOrderService();
    }
}