BootstrapApplicationListener 的作用是产生springCloud上下文 ApplicationContext(bootstrap) 设置为application的上下文
```
DemoServerApplication.main();
    SpringApplication.run();
        SpringApplication.prepareEnvironment();
            SpringApplicationRunListeners.environmentPrepared();
                ...
                BootstrapApplicationListener.onApplicationEvent();
                    BootstrapApplicationListener.bootstrapServiceContext();
                        SpringApplicationBuilder.run();//创建boostrap的ApplicationContext
                            SpringApplication.run();
                                SpringApplication.refreshContext();
                                    SpringApplication.refresh();
                                        AbstractApplicationContext.refresh();
                        addAncestorInitializer()//未来 将boostrap的ApplicationContext设置为application的ApplicationContext的父上下文
```
