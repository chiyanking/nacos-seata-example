SpringBoot 是什么时候初始化Tomcat
```
ServletWebServerApplicationContext.onRefresh()
    ServletWebServerFactory.getWebServer(getSelfInitializer())
```

TomcatServletWebServerFactory 工厂模式获取 WebServer(TomcatWebServer)


TomcatWebServer
    Tomcat tomcat

TomcatWebServer()在init()阶段
        tomcat.start();

ServletWebServerFactoryAutoConfiguration 配置了
    @Bean
    new TomcatServletWebServerFactory()



ConfigurationClassPostProcessor
    ConfigurationClassBeanDefinitionReader
    


参考文章
    https://buaazhangyk.github.io/2018/07/11/spring-boot-3

