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

RabbitListenerEndpoint MethodRabbitListenerEndpoint MultiMethodRabbitListenerEndpoint 如果注解到class的头顶上则产生这个类
SimpleRabbitListenerEndpoint

@RabbitListener -> 对应一个 MethodRabbitListenerEndpoint -> 如果没有指定

RabbitListenerAnnotationBeanPostProcessor 是什么时候被加载到spring中去

public class RabbitBootstrapConfiguration implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //将 RabbitListenerAnnotationBeanPostProcessor 注册到 Beanfactory 中
		if (!registry.containsBeanDefinition(
				RabbitListenerConfigUtils.RABBIT_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME)) {
            //将 RabbitListenerAnnotationBeanPostProcessor 注册到 Beanfactory 中的
			registry.registerBeanDefinition(RabbitListenerConfigUtils.RABBIT_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME,
					new RootBeanDefinition(RabbitListenerAnnotationBeanPostProcessor.class));
		}
        //将 RabbitListenerEndpointRegistry 注册到 Beanfactory 中
		if (!registry.containsBeanDefinition(RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME)) {
			registry.registerBeanDefinition(RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME,
					new RootBeanDefinition(RabbitListenerEndpointRegistry.class));
		}
	}

}

@Order public class RabbitListenerConfigurationSelector implements DeferredImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] { RabbitBootstrapConfiguration.class.getName() };
	}

}

@Import(RabbitListenerConfigurationSelector.class)
public @interface EnableRabbit {

}

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(EnableRabbit.class)
class RabbitAnnotationDrivenConfiguration {

	@EnableRabbit
	static class EnableRabbitConfiguration {

	}

}

@Import(RabbitAnnotationDrivenConfiguration.class)
public class RabbitAutoConfiguration {

}

spring.factories 中有 org.springframework.boot.autoconfigure.EnableAutoConfiguration =
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration

springRabbitMQ 是什么时候启动的 
    RabbitListenerEndpointRegistry 实现了 SmartLifecycle 在spring初始化完成后调用 start 方法 循环启动每一个 MessageListenerContainer 的 start 方法

queue -> InternalConsumer 一个 queue 对应一个 InternalConsumer
com.rabbitmq.client.impl.AMQChannel#doEnqueueRpc 
    一个 AMQChannel 可能对应多个 InternalConsumer 所以在发送请求和接受请求必须先入队



com.rabbitmq.client.impl.AMQConnection#startMainLoop
    com.rabbitmq.client.impl.FrameHandler#readFrame 从 _socket 中读取 读取数据
    com.rabbitmq.client.impl.AMQChannel#handleFrame
        com.rabbitmq.client.impl.CommandAssembler#handleFrame
        // 装配器将 CommandAssembler 将Frame转换为 AMQCommand
    com.rabbitmq.client.impl.ChannelN#processAsync




AMQConnection
    private final AMQChannel _channel0;
    private final FrameHandler _frameHandler;

    public void start(){
        this._frameHandler.initialize(this);
    }

    FrameHandler#initialize(AMQConnection connection) {
        connection.startMainLoop();
    }
    
    AMQConnection#startMainLoop(){
        MainLoop loop = new MainLoop();
        mainLoopThread.start();
    }

这里可以看出来 FrameHandler 才是真正的socket溜处理


org.springframework.amqp.rabbit.connection.CachingConnectionFactory#getCachedChannelProxy
org.springframework.amqp.rabbit.connection.CachingConnectionFactory#getChannel
一个 @RabbitListener 产生一个 MessageListenerContainer

SimpleMessageListenerContainer
    private Set<BlockingQueueConsumer> consumers;


AsyncMessageProcessingConsumer
    private final BlockingQueueConsumer consumer;


BlockingQueueConsumer
    private Channel channel;

ChannelN extends AMQChannel
    private final ConsumerDispatcher dispatcher;


com.rabbitmq.client.ConnectionFactory
com.rabbitmq.client.impl.AMQConnection
com.rabbitmq.client.impl.recovery.RecoveryAwareAMQConnection
com.rabbitmq.client.impl.recovery.AutorecoveringConnection

ConnectionFactory
    
    private final ChannelCachingConnectionProxy connection;
    
    Connection newConnection(ExecutorService executor, List<Address> addrs, String clientProvidedName){
        return 可能返回: AutorecoveringConnection
        return 可能返回：AMQConnection
    }


如果`CachingConnectionFactory`缓存模式是`channel`模式 所有的`channel`会用一个`connection`
    channel.close(); //`ChannelProxy` 这个 channel(`ChannelProxy`) 返回给`CachingConnectionFactory`的缓存(`cachedChannelsNonTransactional`)中


AutorecoveringConnection
    private volatile RecoveryAwareAMQConnection delegate;
    
    public AutorecoveringConnection(...){
        this.cf = new RecoveryAwareAMQConnectionFactory(params, f, addressResolver, metricsCollector);
    }
    public void init() throws IOException, TimeoutException {
        this.delegate = this.cf.newConnection();
        this.addAutomaticRecoveryListener(delegate);
    }
   
conn.init();


AMQConnection 和 RecoveryAwareAMQConnection

RecoveryAwareAMQConnection extends AMQConnection   
RecoveryAwareAMQConnection 和 AMQConnection 是 ChannelManager 不同


RecoveryAwareChannelN extend ChannelN


Cache 缓存模式是 channel
