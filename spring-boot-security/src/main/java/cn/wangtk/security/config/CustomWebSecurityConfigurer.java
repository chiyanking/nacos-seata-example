package cn.wangtk.security.config;

import cn.wangtk.security.filter.SmsCodeAuthenticationSecurityConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Resource
    SmsCodeAuthenticationSecurityConfigurer smsCodeAuthenticationSecurityConfigurer;

    @Resource
    UserDetailsService customUserDetailsService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
        http.apply(smsCodeAuthenticationSecurityConfigurer);//社交登录
        http.authorizeRequests()
                .antMatchers("/index").permitAll()
                .anyRequest().permitAll();

    }
}
