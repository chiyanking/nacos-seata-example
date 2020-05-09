SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `order` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` int(20) DEFAULT NULL COMMENT '用户Id',
  `pay_money` decimal(11,0) DEFAULT NULL COMMENT '付款金额',
  `product_id` int(20) DEFAULT NULL COMMENT '商品Id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `count` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='订单表';

SET FOREIGN_KEY_CHECKS = 1;
