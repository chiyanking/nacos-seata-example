package com.wangtk.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MvcServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MvcServerApplication.class, args);
//        IndexController bean = run.getBean(IndexController.class);
//        bean.index();
//        System.out.println(bean);
    }
}
