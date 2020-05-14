package cn.wangtk.order.sharding.mapper;

import cn.wangtk.order.sharding.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

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
