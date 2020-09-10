package com.wangtk.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
    @Resource
    LoginController loginController;

    @GetMapping("index")
    public String index() {
        return "";
    }

}
