package com.wangtk.mvc.controller;

import com.wangtk.mvc.dto.WParameter;
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
    public String testParam(WParameter wParameter, String name, String value) {
        System.out.println(wParameter);
        String testlocal = "王麻子";
        int aaaaa= 1;
        long bbbb =1L;
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
