#!/bin/bash
#批量新建数据表
for y in {1..5};do
mysql -uroot -h127.0.0.1 -pdianji888 -e "use seata-order-$y; SOURCE ./seata-order.sql;"
done