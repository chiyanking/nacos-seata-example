## docker搭建nacos server集群



在学习周阳老师的SpringCloud的《SpringCloud Alibaba Nacos服务注册和配置中心》时，自己通过docker搭建了nacos server集群。

参考地址：

1. [简书](https://www.jianshu.com/p/07ad6e817533)
2. [nacos文档](https://nacos.io/zh-cn/docs/quick-start-docker.html)
3. [github](https://github.com/nacos-group/nacos-docker)

软件版本：

1、nacos:1.4.1

2、mysql:5.7

3、nginx:latest

### 1、配置nacos和MySQL

#### 1.1首先编写好docker-compose.yaml文件

```yaml
version: "3"
services:
  mysql:
    container_name: mysql
    # 5.7
    image: mysql:5.7
    environment:
      # mysql root用户密码
      MYSQL_ROOT_PASSWORD: root
    command:
      --default-authentication-plugin=mysql_native_password
      --character-set-server=utf8mb4
      --collation-server=utf8mb4_general_ci
      --explicit_defaults_for_timestamp=true
      --lower_case_table_names=1
      --max_allowed_packet=128M;
    volumes:
      # mysql的数据文件
      - ./mysql/data:/var/lib/mysql
      # mysql配置文件
      - ./mysql/config:/etc/mysql/conf.d
    ports:
      - "3306:3306"
  nacos1:
    hostname: nacos1
    container_name: nacos1
    image: nacos/nacos-server:latest
    volumes:
      # 需要添加mysql8的插件
      #- ./nacos/plugins/mysql/:/home/nacos/plugins/mysql/
      # 把日志文件映射出来
      - ./nacos/cluster-logs/nacos1:/home/nacos/logs
      # 把配置文件映射出来
      - ./nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties
    
    environment:                       # 设置环境变量,相当于docker run命令中的-e
      - JVM_XMS=512m
      - JVM_XMX=512m
      - JVM_XMN=128m
      #- MODE=standalone   #单机版
    ports:
      - "8848:8848"
      - "9555:9555"
    env_file:
        # 集群配置文件
      - ./nacos/env/nacos-hostname.env
    restart: always
    depends_on:
      - mysql
  nacos2:
    hostname: nacos2
    image: nacos/nacos-server:latest
    container_name: nacos2
    volumes:
      #- ./nacos/plugins/mysql/:/home/nacos/plugins/mysql/
      - ./nacos/cluster-logs/nacos2:/home/nacos/logs
      - ./nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties
    environment:                        # 设置环境变量,相当于docker run命令中的-e
      - JVM_XMS=512m
      - JVM_XMX=512m
      - JVM_XMN=128m
    ports:
      - "8849:8848"
    env_file:
      - ./nacos/env/nacos-hostname.env
    restart: always
    depends_on:
      - mysql
  nacos3:
    hostname: nacos3
    image: nacos/nacos-server:latest
    container_name: nacos3
    volumes:
      #- ./nacos/plugins/mysql/:/home/nacos/plugins/mysql/
      - ./nacos/cluster-logs/nacos3:/home/nacos/logs
      - ./nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties
    environment:                      # 设置环境变量,相当于docker run命令中的-e
      - JVM_XMS=512m
      - JVM_XMX=512m
      - JVM_XMN=128m
    ports:
      - "8850:8848"
    env_file:
      - ./nacos/env/nacos-hostname.env
    restart: always
    depends_on:
      - mysql
```

**注意1：**docker语法要求严格，这里不能用tab,只能用空格，不然会报错。

报错信息：ERROR: yaml.scanner.ScannerError: while scanning for the next token found character '\t' that cannot

参考地址：https://blog.csdn.net/tianshuhao521/article/details/84592114

![image-20210322213324948](assets/image-20210322213324948.png)

**注意2：**要设置JVM环境变量，不然占用内存极大

![image-20210322213612998](assets/image-20210322213612998.png)



**注意3：**使用mysql8要加下面的内容（我们目前没有使用）

我们还需要把上述的配置文件补齐

**my.cnf**

```csharp
[mysqld]
default-time_zone='+8:00'
```

这是mysql8的配置文件，就是标准的mysql配置文件，我就改了一下时区，其他配置大家可以自定义修改。这个mysql实例不仅仅可以给nacos使用，还可以给开发环境其他应用使用。这也是为什么跳出官方给的example，自定义安装mysql的原因之一。

#### 1.2  配置nacos,创建custom.properties和nacos-hostname.env文件

注意，先把./nacos/init.d/custom.properties这个文件建好，这个- ./nacos/env/nacos-hostname.env文件也建好，放在相应的文件夹地方。

**custom.properties**

```shell
#spring.security.enabled=false
#management.security=false
#security.basic.enabled=false
#nacos.security.ignore.urls=/**
#management.metrics.export.elastic.host=http://localhost:9200
# metrics for prometheus
management.endpoints.web.exposure.include=*

# metrics for elastic search
#management.metrics.export.elastic.enabled=false
#management.metrics.export.elastic.host=http://localhost:9200

# metrics for influx
#management.metrics.export.influx.enabled=false
#management.metrics.export.influx.db=springboot
#management.metrics.export.influx.uri=http://localhost:8086
#management.metrics.export.influx.auto-create-db=true
#management.metrics.export.influx.consistency=one
#management.metrics.export.influx.compressed=true

```



**nacos-hostname.env**

```shell
#nacos dev env
PREFER_HOST_MODE=hostname
# docker实例别名，可以换成ip
NACOS_SERVERS=nacos1:8848 nacos2:8848 nacos3:8848
MYSQL_SERVICE_HOST=mysql
# 指定保存数据的数据库名称
MYSQL_SERVICE_DB_NAME=nacos_devtest
# 访问mysql端口
MYSQL_SERVICE_PORT=3306
# 访问mysql的用户名
MYSQL_SERVICE_USER=root
# 访问mysql的密码
MYSQL_SERVICE_PASSWORD=root
```





### 2.先把mysql实例运行起来(千万不要直接docker-compose up -d把所有的实例都运行起来)



```undefined
docker-compose up -d mysql
```

先把需要事先配置的都先配置好，先创建对应的mysql用户：
在mysql实例中先创建好一个“nacos”的用户名，密码是“nacos”，并且把相关的数据库创建好，并允许该用户可以访问
ps:这个操作需要进入mysql实例里面，可通过

```dockerfile
docker exec -it {mysql实例ID} /bin/bash
```

进入mysql实例后，通过

```mysql
# 登录mysql控制台，密码为docker-compose.yaml里面配置的root
mysql -uroot -p
```



```mysql
use mysql;
//创建用户
CREATE USER 'nacos'@'%' identified by 'nacos';
//赋予权限
grant all privileges on nacos_devtest.* to 'nacos'@'%' with GRANT OPTION;
flush privileges;
```

再创建一个名叫“nacos_devtest”的数据库
**nacos_devtest数据库中所有的数据表**



```mysql
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `permission` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gmt_create` bigint DEFAULT NULL,
  `gmt_modified` bigint DEFAULT NULL,
  UNIQUE KEY `idx_role_resource` (`role`,`permission`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;
```

### 3.启动nacos server集群

```undefined
docker-compose up -d
```

执行上述命令，nacos1，nacos2，nacos3实例就会启动，后续可以通过浏览器访问

```cpp
http://{ip}:8848/nacos
```

{ip}变量就是指的宿主机的ip



### 4、配置nginx

#### 4.1 编写好docker-compose.yaml文件

```shell
version: "3"
services:
  nginx:
    restart: always
    image: nginx:latest
    container_name: nginx
    ports:
      - "8080:80"
      - "443:443"
    volumes:
      - ./config/nginx/nginx.conf:/etc/nginx/nginx.conf
      - ./data/nginx/:/usr/share/nginx/html/
      - ./log/nginx/:/var/log/nginx/

```

在相应的文件夹位置编写nginx.conf文件

```shell
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #加上下面这句可以让单个上传文件在100MB内  
    client_max_body_size 100m;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

   upstream nacos { 
      server  ip地址:8848 weight=10; 
      server  ip地址:8849 weight=10; 
      server  ip地址:8850 weight=10; 
   } 

	server{ 
	    listen 80; 
	    server_name  ip地址; 
	    location / { 
	        proxy_pass         http://nacos; 

	    } 
	}
}
```

启动

```shell
docker-compose up -d
```



测试：

![image-20210322220027152](assets/image-20210322220027152.png)





注意问题：单机版和集群版搭建是不一样的，注意细节，详细看官网搭建。



## nacos参数信息

https://nacos.io/zh-cn/docs/quick-start-docker.html

name	description	option
MODE	cluster模式/standalone模式	cluster/standalone default cluster
NACOS_SERVERS	nacos cluster地址	eg. ip1,ip2,ip3
PREFER_HOST_MODE	是否支持hostname	hostname/ip default ip
NACOS_SERVER_PORT	nacos服务器端口	default 8848
NACOS_SERVER_IP	多网卡下的自定义nacos服务器IP	
SPRING_DATASOURCE_PLATFORM	standalone 支持 mysql	mysql / empty default empty
MYSQL_MASTER_SERVICE_HOST	mysql 主节点host	
MYSQL_MASTER_SERVICE_PORT	mysql 主节点端口	default : 3306
MYSQL_MASTER_SERVICE_DB_NAME	mysql 主节点数据库	
MYSQL_MASTER_SERVICE_USER	数据库用户名	
MYSQL_MASTER_SERVICE_PASSWORD	数据库密码	
MYSQL_SLAVE_SERVICE_HOST	mysql从节点host	
MYSQL_SLAVE_SERVICE_PORT	mysql从节点端口	default :3306
MYSQL_DATABASE_NUM	数据库数量	default :2
JVM_XMS	-Xms	default :2g
JVM_XMX	-Xmx	default :2g
JVM_XMN	-Xmn	default :1g
JVM_MS	-XX:MetaspaceSize	default :128m
JVM_MMS	-XX:MaxMetaspaceSize	default :320m
NACOS_DEBUG	开启远程调试	y/n default :n
TOMCAT_ACCESSLOG_ENABLED	server.tomcat.accesslog.enabled	default :false

## 快速搭建

### 1、下载脚本

```shell
https://gitee.com/ysdxhsw/docker-compose-nacos-mysql.git
```

### 2、进入docker-nacos

```shell
docker-compoes up -d 
```

### 3、进入docker-nginx目录

```shell
docker-compoes up -d 
```

### 4、访问

```shell
http://{ip}:8080/nacos
```

