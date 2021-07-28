SpringBoot 是什么时候初始化Tomcat
	webServer的启动跟异常关闭过程跟`ServletWebServerApplicationContext`覆盖父类`GenericWebApplicationContext`的5个方法`postProcessBeanFactory`、`refresh`、`onRefresh`、`finishRefresh`、`onClose`。除`refresh方法`之外，另外4个方法都会在父抽象类`AbstractApplicationContext`的`refresh`方法中执行，具体的执行次序及作用可以详见对该类的分析。

1）首先会被调用的是`postProcessBeanFactory`方法，方法会执行两步。
2）按照步骤，继续会被调用的是`onRefresh`方法，在`onRefresh`方法中会调用`createWebServer`方法，该方法会创建一个`webServer`。
3）再次被调用的会是`finishRefresh`，在该方法中会调用`startWebServer`，真正将webServer进行启动。启动成功之后，会创建一个`ServletWebServerInitializedEvent`的事件并将其发布出去。
4）最后关闭的方法，在`ApplicationContext`关闭时也会调用`stopAndReleaseWebServer`将对应的webServer关闭掉。

springBoot启动过程tomcat源码 部分做了省略

```
ServletWebServerApplicationContext#onRefresh()
protected void onRefresh() {
		super.onRefresh();
		try {
			createWebServer();
		}catch (Throwable ex) {
      
		}
}
private void createWebServer() {
		WebServer webServer = this.webServer;
		ServletContext servletContext = getServletContext();
		if (webServer == null && servletContext == null) {
        //从Beanfacotry获取WebServerFactory的子类TomcatServletWebServerFactory
			ServletWebServerFactory factory = getWebServerFactory();
			this.webServer = factory.getWebServer(getSelfInitializer());
		}
		initPropertySources();
}
TomcatServletWebServerFactory#getWebServer()
public WebServer getWebServer(ServletContextInitializer... initializers) {
   Tomcat tomcat = new Tomcat();
  	//...省略不表要的代码
   return getTomcatWebServer(tomcat);
}

protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
		return new TomcatWebServer(tomcat, getPort() >= 0);
}
public TomcatWebServer(Tomcat tomcat, boolean autoStart) {
		this.tomcat = tomcat;
		initialize();
}
private void initialize() throws WebServerException {
		synchronized (this.monitor) {
			try {
				//...省略不表要的代码
				this.tomcat.start(); //调用tomcat的生命周期中的初始化代码
        //...省略不表要的代码
			} catch (Exception ex) {
				stopSilently();
				destroySilently();
			}
		}
}
```

ServletWebServerFactoryAutoConfiguration 配置了
    @Bean
    new TomcatServletWebServerFactory()



ConfigurationClassPostProcessor
    ConfigurationClassBeanDefinitionReader

参考文章
    Spring boot-嵌入式web server启动过程分析 
	https://buaazhangyk.github.io/2018/07/11/spring-boot-3

tomcat 启动原理
	https://chenmingyu.top/tomcat-source-code/	



ServletWebServerFactory -> WebServer

web.xml -> ServletContainerInitializer -> TomcatStarter

public class TomcatWebServer implements WebServer {
    
    private final Tomcat tomcat;
    
    
}

