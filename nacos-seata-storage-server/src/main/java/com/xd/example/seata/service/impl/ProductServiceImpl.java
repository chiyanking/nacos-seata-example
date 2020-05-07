package com.xd.example.seata.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xd.example.seata.domain.Product;
import com.xd.example.seata.mapper.ProductMapper;
import com.xd.example.seata.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓储服务 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {


    @Override
    @GlobalTransactional(name = "prex-create-order", rollbackFor = Exception.class)
    public boolean reduceCount(Integer productId, Integer amount) {
        log.info("商品Id:{},商品数量:{}", productId, amount);
        log.info("当前 XID: {}", RootContext.getXID());
        // 检查库存
        checkStock(productId, amount);
        log.info("开始扣减 {} 库存", productId);
        Integer record = baseMapper.reduceCount(productId, amount);

        log.info("结束扣减 {} 库存结果:{}", productId, record > 0 ? "操作成功" : "扣减库存失败");
        return false;
    }

    @Override
    public boolean resetCount(Integer productionId, Integer count) {
        baseMapper.deleteUndoLog();
        return update(Wrappers.<Product>lambdaUpdate().eq(Product::getProductId, productionId).set(Product::getCount, count));
    }

    public void checkStock(Integer productId, Integer requiredAmount) {
        log.info("检查 {} 库存", productId);
        Integer count = baseMapper.selectOne(Wrappers.<Product>lambdaQuery().select(Product::getCount).eq(Product::getProductId, productId)).getCount();
        System.out.println(count);
        if (count < requiredAmount) {
            log.warn("{} 库存不足，当前库存:{}", productId, count);
            throw new RuntimeException("库存不足");
        }
    }

}
