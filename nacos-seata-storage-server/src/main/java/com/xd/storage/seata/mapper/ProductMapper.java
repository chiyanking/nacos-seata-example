package com.xd.storage.seata.mapper;

import com.xd.storage.seata.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 仓储服务 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface ProductMapper extends BaseMapper<Product> {

    @Update("UPDATE product SET count = count - #{amount} WHERE product_id = #{productId}")
    Integer reduceCount(@Param("productId") Integer productId, @Param("amount") Integer amount);

    @Delete("delete from undo_log")
    void deleteUndoLog();
}
