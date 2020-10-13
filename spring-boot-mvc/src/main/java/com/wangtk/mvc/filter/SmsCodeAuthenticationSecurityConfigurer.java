//package com.wangtk.security.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SmsCodeAuthenticationSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    public SmsCodeAuthenticationSecurityConfigurer() {
//        System.out.println("name");
//    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //自定义SmsCodeAuthenticationFilter过滤器
//        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
//        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
////        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(merryyouAuthenticationFailureHandler);
//
//        //设置自定义SmsCodeAuthenticationProvider的认证器userDetailsService
//        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
//        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
//        //在UsernamePasswordAuthenticationFilter过滤前执行
//        http.authenticationProvider(smsCodeAuthenticationProvider)
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
