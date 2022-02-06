package cn.wangtk.security;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class SecurityApplicationTests {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println(classPathXmlApplicationContext);

    }

}
