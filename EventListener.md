@EventListener 注解是被 EventListenerMethodProcessor 在spring的初始化阶段处理的
```
org.springframework.context.support.AbstractApplicationContext.refresh();
        org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory);
            beanFactory.preInstantiateSingletons()
                org.springframework.context.event.EventListenerMethodProcessor.afterSingletonsInstantiated()
```

@RabbitListener 注解是被 RabbitListenerAnnotationBeanPostProcessor 在spring的初始化阶段处理的
```
org.springframework.context.support.AbstractApplicationContext.refresh();
        org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory);
            beanFactory.preInstantiateSingletons()
                org.springframework.context.event.RabbitListenerAnnotationBeanPostProcessor.afterSingletonsInstantiated()
```
