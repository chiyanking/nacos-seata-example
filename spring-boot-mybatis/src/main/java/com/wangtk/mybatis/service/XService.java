package com.wangtk.mybatis.service;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class XService {

    public XService() {
        System.out.println("初始化 XService");
    }

    @Resource
    YService yService;


    @Transactional
    public String getX() {
        return "x";
    }


}
