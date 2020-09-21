package com.wangtk.mybatis.service;

import com.wangtk.mybatis.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class YServiceTest extends BaseTest {
    @Resource
    XService xService;

    @Test
    public void testGetY() {
        xService.getX();
    }
}