package com.wangtk.security.config;

import com.wangtk.security.dto.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyNamespaceHandlerTest {
    @Test
    public void testInit() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        People p = (People) ctx.getBean("cutesource");
        System.out.println(p.getId());
        System.out.println(p.getName());
        System.out.println(p.getAge());

    }
}