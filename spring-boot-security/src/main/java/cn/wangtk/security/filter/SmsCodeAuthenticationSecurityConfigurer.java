package cn.wangtk.security.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class SmsCodeAuthenticationSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    public SmsCodeAuthenticationSecurityConfigurer() {
        System.out.println("name");
    }

    @Resource
    SmsCodeAuthenticationFilter smsCodeAuthenticationFilter;
    @Resource
    JSONAuthenticationFailureHandler jsonAuthenticationFailureHandler;
    @Resource
    JSONAuthenticationSuccessHandler jsonAuthenticationSuccessHandler;


    @Resource
    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //自定义SmsCodeAuthenticationFilter过滤器
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(jsonAuthenticationFailureHandler);

        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        //设置自定义SmsCodeAuthenticationProvider的认证器userDetailsService
//        smsCodeAuthenticationProvider.setUserDetailsService(customUserDetailsService);

        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
