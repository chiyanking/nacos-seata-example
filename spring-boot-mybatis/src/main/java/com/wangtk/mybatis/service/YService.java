package com.wangtk.mybatis.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class YService {
    @Resource
    XService xService;


    @Transactional
    public String getY() {
        return "y";
    }
}
