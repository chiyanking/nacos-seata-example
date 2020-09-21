package com.wangtk.mybatis.dao;

import com.wangtk.mybatis.BaseTest;
import com.wangtk.mybatis.entity.UserDO;
import com.wangtk.mybatis.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserMapperTest extends BaseTest {


    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

    @Test
    public void testFindLastLoginDateByOrgId() {
        List<UserDO> userLists;
        userLists = userService.getListByOrgId(2l);
        System.out.println(userLists);
    }
}