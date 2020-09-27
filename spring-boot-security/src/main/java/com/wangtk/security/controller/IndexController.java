package com.wangtk.security.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController implements InitializingBean {
    public IndexController() {
        System.out.println("a");
    }

    @Resource
    TestParamController testParamController;

    @GetMapping("index")
    public String index() {
        return "";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("set name");
    }
}
