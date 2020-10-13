package com.wangtk.mvc.resolvableType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ATypeService implements TypeService<String, String> {

    @Override
    public void getServiceType(String name, String value) {
        log.info("name {} value {}", name, value);
        System.out.println("张三李四");
    }
}
