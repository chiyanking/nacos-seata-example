package cn.wangtk.security.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    SmsCodeAuthenticationSecurityConfigurer smsCodeAuthenticationSecurityConfigurer;

    @Resource
    UserDetailsService userDetailsService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
        http.apply(smsCodeAuthenticationSecurityConfigurer);//社交登录


    }
}
