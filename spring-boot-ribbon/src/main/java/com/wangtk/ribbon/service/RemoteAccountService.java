package com.wangtk.ribbon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "kangaroo-user")
public interface RemoteAccountService {

    @GetMapping("/remote/uri/getUriList")
    List getUriList();
}
