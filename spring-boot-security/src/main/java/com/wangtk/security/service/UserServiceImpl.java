//package com.wangtk.security.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class UserServiceImpl implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("查询用户");
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("张三李四王嘛");
//        userEntity.setPassword("password");
//        return userEntity;
//    }
//}
