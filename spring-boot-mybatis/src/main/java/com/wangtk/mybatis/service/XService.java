package com.wangtk.mybatis.service;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class XService {


    @Resource
    YService yService;


    @Transactional
    public String getX() {
        return "x";
    }


}
