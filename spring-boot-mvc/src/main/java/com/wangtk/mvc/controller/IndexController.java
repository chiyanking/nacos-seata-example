package com.wangtk.mvc.controller;

import com.wangtk.mvc.service.IndexService;
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
    @Resource
    IndexService indexService;

    @GetMapping("index")
    public String index() {
        return indexService.getIndexService();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("set name");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 0; i++) {
            System.out.println(i);
        }
    }
}
