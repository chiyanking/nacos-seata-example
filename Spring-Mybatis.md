



Servlet 容器的SCI（ServletContainerInitializer）接口
SCI：Servlet容器（Tomcat）提供的初始化 Servlet 容器本身的接口，可替换web.xml 





@Import(MapperScannerRegistrar.class)
public @interface MapperScan {
}


MapperScannerRegistrar -> MapperScannerConfigurer -> ClassPathMapperScanner -> MapperFactoryBean

MapperFactoryBean.getObject()
    ->org.mybatis.spring.SqlSessionTemplate.getMapper
    ->com.baomidou.mybatisplus.core.MybatisConfiguration.getMapper
    ->com.baomidou.mybatisplus.core.MybatisMapperRegistry.getMapper
    ->com.baomidou.mybatisplus.core.override.MybatisMapperProxyFactory.newInstance(org.apache.ibatis.session.SqlSession)
    ->org.apache.ibatis.binding.MapperProxyFactory.newInstance(org.apache.ibatis.session.SqlSession)
    ->org.apache.ibatis.binding.MapperProxy


SpringManagedTransactionFactory 生产 SpringManagedTransaction

