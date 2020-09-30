package com.wangtk.mybatis.dao;

import com.alibaba.fastjson.JSON;
import com.wangtk.mybatis.BaseTest;
import com.wangtk.mybatis.entity.UserDO;
import com.wangtk.mybatis.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserMapperTest extends BaseTest {


    @Resource
    UserService userService;

    @Test
    public void testFindLastLoginDateByOrgId() {
        List<UserDO> userLists = userService.getListByOrgId(2l);
        System.out.println(JSON.toJSONString(userLists));
    }


    @Test
    public void testUpdateUserNameByOrgId() {
        userService.updateName(2l, "李四张三");
    }
}