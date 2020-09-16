package com.wangtk.security.service;

import com.wangtk.security.controller.IndexController;
import com.wangtk.security.controller.LoginController;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexService {


    @Resource
    IndexController indexController;
    @Resource
    LoginController loginController;


    public void getIndexService() {

    }
}

