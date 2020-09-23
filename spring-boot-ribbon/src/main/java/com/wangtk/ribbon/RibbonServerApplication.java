package com.wangtk.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableFeignClients//开启Feign
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RibbonServerApplication.class, args);
        System.out.println(context);
    }
}
