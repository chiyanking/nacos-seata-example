package com.xd.example.seata.mapper;

import com.xd.example.seata.domain.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface AccountMapper extends BaseMapper<Account> {


    @Update("UPDATE account SET balance = balance - #{balance} WHERE id = #{userId}")
    Integer reduceBalance(@Param("userId") Integer userId, @Param("balance") BigDecimal balance);

    @Delete("delete from undo_log")
    void deleteUndoLog();
}
