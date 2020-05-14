package com.xd.storage.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
		, com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration.class})
public class NacosSeataStorageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosSeataStorageServerApplication.class, args);
	}

}
