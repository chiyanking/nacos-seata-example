package com.wangtk.mvc.service;

import com.wangtk.mvc.controller.IndexController;
import com.wangtk.mvc.controller.TestParamController;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexServiceImpl implements IndexService {


    @Resource
    IndexController indexController;
    @Resource
    TestParamController testParamController;


    public String getIndexService() {
        return "indexService";
    }
}

