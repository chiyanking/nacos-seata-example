package com.xd.example.seata.mapper;

import com.xd.example.seata.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Delete("delete from undo_log")
    void deleteUndoLog();
}
