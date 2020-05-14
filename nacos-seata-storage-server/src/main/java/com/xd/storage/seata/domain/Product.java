package com.xd.storage.seata.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 仓储服务
 * </p>
 *
 * @author lihaodong
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品Id
     */
    private Integer productId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer count;


}
