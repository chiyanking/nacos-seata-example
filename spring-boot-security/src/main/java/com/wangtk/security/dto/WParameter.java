package com.wangtk.security.dto;

import lombok.Data;

@Data
public class WParameter {

    public WParameter() {
        System.out.println("初始化参数");
    }

    private String testName;
    private String testValue;
}
