package cn.wangtk.storage.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
		, com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration.class})
public class ShardingNacosSeataStorageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingNacosSeataStorageServerApplication.class, args);
	}

}
