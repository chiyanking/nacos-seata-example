package com.wangtk.example.seata.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MyBatisPlusConfig
 * @Description mybatis配置 并配置seata数据代理
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-11-25 11:21
 * @Version 1.0
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
