package cn.wangtk.account.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
        , com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration.class})
@MapperScan("com.xd.example.seata.mapper")
public class NacosSeataAccountServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSeataAccountServerApplication.class, args);
    }

}
