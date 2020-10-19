package com.wangtk.mvc.resolvableType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringService<T> implements TypeService<String,T> {

    @Override
    public void getServiceType(String name, T value) {

    }
}
