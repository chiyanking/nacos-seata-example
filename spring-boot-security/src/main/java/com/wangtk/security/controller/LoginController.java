package com.wangtk.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    IndexController indexController;

    @GetMapping("login")

    public String login() {
        return "";
    }

}
