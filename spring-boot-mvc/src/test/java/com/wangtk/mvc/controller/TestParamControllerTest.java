package com.wangtk.mvc.controller;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;

@Slf4j
public class TestParamControllerTest extends TestCase {

    public void testTestParam() {

        DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

        Method[] methods = TestParamController.class.getMethods();
        for (Method method : methods) {

            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            log.info("{} : {}", method.getName(), parameterNames);
        }

    }
}