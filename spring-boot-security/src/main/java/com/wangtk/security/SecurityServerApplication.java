package com.wangtk.security;

import com.wangtk.security.controller.IndexController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SecurityServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecurityServerApplication.class, args);
        IndexController bean = run.getBean(IndexController.class);
        bean.index();
        System.out.println(bean);
    }
}
