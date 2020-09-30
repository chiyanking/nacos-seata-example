package com.wangtk.mybatis.service;

import com.wangtk.mybatis.dao.UserMapper;
import com.wangtk.mybatis.entity.UserDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserService {
    @Resource
    UserMapper userMapper;

    @Transactional
    public List<UserDO> getListByOrgId(Long orgId) {
        userMapper.getListByOrgId(orgId);
        userMapper.updateById(userMapper.getListByOrgId(orgId).get(0).getName(), orgId);
        return userMapper.getListByOrgId(orgId);
    }

    //    @Transactional
    public void updateName(Long orgId, String name) {
        userMapper.updateById(name, orgId);
        userMapper.updateById(name, orgId);
    }
}
