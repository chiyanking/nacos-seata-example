package cn.wangtk.storage.sharding.service;

import cn.wangtk.storage.sharding.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓储服务 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
public interface IProductService extends IService<Product> {

    boolean reduceCount(Integer amount, Integer productId);

    boolean resetCount(Integer productionId, Integer count);

}
