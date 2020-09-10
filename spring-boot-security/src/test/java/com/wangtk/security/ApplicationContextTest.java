package com.wangtk.security;

import com.wangtk.security.controller.IndexController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SecurityServerApplication.class);
        IndexController bean = applicationContext.getBean(IndexController.class);
        bean.index();
    }

}
