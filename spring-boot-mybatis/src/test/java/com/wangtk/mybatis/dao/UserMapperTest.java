package com.wangtk.mybatis.dao;

import com.wangtk.mybatis.BaseTest;
import com.wangtk.mybatis.entity.UserDO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserMapperTest extends BaseTest {


    @Resource
    UserMapper userMapper;

    @Test
    public void testFindLastLoginDateByOrgId() {
        List<UserDO> all = userMapper.getAll();
        System.out.println(all);
    }
}