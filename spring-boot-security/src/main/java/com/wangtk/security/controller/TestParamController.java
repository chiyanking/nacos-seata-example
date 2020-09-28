package com.wangtk.security.controller;

import com.wangtk.security.dto.WParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class TestParamController {

    @Resource
    IndexController indexController;

    @GetMapping("testParam")
    public String testParam(WParameter wParameter) {
        System.out.println(wParameter);
        return new Date().toString();
    }

    @RequestMapping("testResponse")
    public WParameter testResponse() {
        System.out.println("testResponse");
        return new WParameter();
    }

    @ResponseBody
    @GetMapping("testResponseBody")
    public WParameter testResponseBody() {
        System.out.println("testResponseBody");
        return new WParameter();
    }

}
