package com.xd.order.seata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
		, com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration.class})
@MapperScan("com.xd.example.seata.mapper")
public class NacosSeataOrderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosSeataOrderServerApplication.class, args);
	}

}
