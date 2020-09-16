package com.wangtk.security.controller;

import com.wangtk.security.dto.WParameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    IndexController indexController;

    @GetMapping("login")
    public String login(WParameter wParameter) {
        System.out.println(wParameter);
        return "";
    }

}
