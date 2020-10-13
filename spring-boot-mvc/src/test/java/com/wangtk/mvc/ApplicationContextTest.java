package com.wangtk.mvc;

import com.wangtk.mvc.controller.IndexController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MvcServerApplication.class);
        IndexController bean = applicationContext.getBean(IndexController.class);
        bean.index();
    }

}
