package com.wangtk.ribbon.service;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    RemoteAccountService remoteAccountService;
    @Resource
    RemoteSmsService remoteSmsService;

    public void getOrderService() {
        System.out.println("调用用户信息");
        List uriList = remoteAccountService.getUriList();
        System.out.println(uriList);
        String shareRegisterContent = remoteSmsService.getValueByName("shareRegisterContent");
        System.out.println(shareRegisterContent);
    }
}
