package cn.wangtk.order.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
        , com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration.class
//        , org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration.class
})
@MapperScan("com.wangtk.example.seata.mapper")
public class ShardingNacosSeataOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingNacosSeataOrderServerApplication.class, args);
    }

}
