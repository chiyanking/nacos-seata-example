package cn.wangtk.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @GetMapping("getIndexInfo")
    public String getIndexInfo(String value) {
        return "访问成功";
    }


}
