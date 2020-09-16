package com.wangtk.security.resolvableType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WTypeService<T, S> implements TypeService<T, S> {

    @Override
    public void getServiceType(T name, S value) {
        System.out.println("T " + name);
        System.out.println("S " + value);
    }
}
