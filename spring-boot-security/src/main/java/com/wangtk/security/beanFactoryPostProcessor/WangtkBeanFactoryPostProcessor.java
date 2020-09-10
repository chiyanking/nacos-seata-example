package com.wangtk.security.beanFactoryPostProcessor;

import com.wangtk.security.dto.People;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WangtkBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public WangtkBeanFactoryPostProcessor() {

        System.out.println("create WangtkBeanFactoryPostProcessor");

    }

    @Bean
    public People getPeople() {
        return new People();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("WangtkBeanFactoryPostProcessor processor");

    }
}