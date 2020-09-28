package com.wangtk.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceConfig {


    @Bean
    public IRule getIRule() {

        return new WeightedResponseTimeRule();
    }
}
