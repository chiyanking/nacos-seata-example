package com.wangtk.ribbon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kangaroo-ins")
public interface RemoteSmsService {

    @RequestMapping(value = "/web/sysParam/aggregation/getValueByName", method = RequestMethod.GET)
    String getValueByName(@RequestParam("name") String name);
}