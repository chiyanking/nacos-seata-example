package com.wangtk.mybatis.dao;

import com.wangtk.mybatis.entity.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: UserBusiness
 * Description:
 *
 * @author: CodeGenerator
 * @date: 2018/11/16 16:07
 * Copyright (C) 杭州同基汽车科技有限公司
 */

public interface UserMapper {

    Boolean updateById(@Param("name") String name, @Param("orgId") Long orgId);

    UserDO getById(Long userId);

    List<UserDO> getListByOrgId(@Param("orgId") Long orgId);

    List<UserDO> getAll();
}
